package io.apicurio.umg.pipe.java;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.jboss.forge.roaster.model.source.FieldSource;
import org.jboss.forge.roaster.model.source.JavaClassSource;
import org.jboss.forge.roaster.model.source.JavaInterfaceSource;

import io.apicurio.umg.models.concept.EntityModel;
import io.apicurio.umg.models.concept.PropertyModel;
import io.apicurio.umg.models.concept.PropertyType;

/**
 * Creates the fields for each entity implementation.  This is done by iterating over all leaf entities
 * and collecting all the properties for it.  One field is created for each property.
 *
 * @author eric.wittmann@gmail.com
 */
public class CreateImplFieldsStage extends AbstractJavaStage {

    @Override
    protected void doProcess() {
        getState().getConceptIndex().findEntities("").stream().filter(entity -> entity.isLeaf()).forEach(entity -> {
            createEntityImplFields(entity);
        });
    }

    private void createEntityImplFields(EntityModel entity) {
        JavaClassSource javaEntityImpl = lookupJavaEntityImpl(entity);
        Collection<PropertyModel> allProperties = getState().getConceptIndex().getAllEntityProperties(entity);

        allProperties.forEach(property -> {
            createEntityImplField(javaEntityImpl, property);
        });
    }

    private void createEntityImplField(JavaClassSource javaEntityImpl, PropertyModel property) {
        boolean isStarProperty = false;
        if ("*".equals(property.getName())) {
            PropertyType mappedType = PropertyType.builder()
                    .nested(Collections.singleton(property.getType()))
                    .map(true)
                    .build();
            property = PropertyModel.builder().name("_items").type(mappedType).build();
            isStarProperty = true;
        } else if (property.getName().startsWith("/") && (isEntity(property) || isPrimitive(property))) {
            if (property.getCollection() == null) {
                error("Regex property defined without a collection name: " + javaEntityImpl.getCanonicalName() + "::" + property);
                return;
            }
            PropertyType collectionPropertyType = PropertyType.builder()
                    .nested(Collections.singleton(property.getType()))
                    .map(true)
                    .build();
            property = PropertyModel.builder().name(property.getCollection()).type(collectionPropertyType).build();
        }

        String fieldName = getFieldName(property);
        String fieldType = "String";

        if (fieldName == null) {
            warn("Could not figure out field name for property: " + property);
            return;
        }

        if (isPrimitive(property)) {
            Class<?> pType = primitiveTypeToClass(property.getType());
            javaEntityImpl.addImport(pType);
            fieldType = pType.getSimpleName();
        } else if (isEntity(property)) {
            JavaInterfaceSource javaTypeEntity = resolveCommonJavaEntity(javaEntityImpl.getPackage(), property.getType().getSimpleType());
            javaEntityImpl.addImport(javaTypeEntity);
            fieldType = javaTypeEntity.getName();
        } else if (isPrimitiveList(property)) {
            Class<?> pType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityImpl.addImport(pType);
            javaEntityImpl.addImport(List.class);
            fieldType = "List<" + pType.getSimpleName() + ">";
        } else if (isPrimitiveMap(property)) {
            Class<?> pType = primitiveTypeToClass(property.getType().getNested().iterator().next());
            javaEntityImpl.addImport(pType);
            javaEntityImpl.addImport(Map.class);
            fieldType = "Map<String, " + pType.getSimpleName() + ">";
        } else if (isEntityList(property)) {
            PropertyType listType = property.getType().getNested().iterator().next();
            JavaInterfaceSource javaTypeEntity = resolveCommonJavaEntity(javaEntityImpl.getPackage(), listType.getSimpleType());
            javaEntityImpl.addImport(javaTypeEntity);
            javaEntityImpl.addImport(List.class);
            fieldType = "List<" + javaTypeEntity.getName() + ">";
        } else if (isEntityMap(property)) {
            PropertyType mapType = property.getType().getNested().iterator().next();
            JavaInterfaceSource javaTypeEntity = resolveCommonJavaEntity(javaEntityImpl.getPackage(), mapType.getSimpleType());
            javaEntityImpl.addImport(javaTypeEntity);
            javaEntityImpl.addImport(Map.class);
            fieldType = "Map<String, " + javaTypeEntity.getName() + ">";
        } else {
            warn("Field not created - property type not supported: " + property);
        }

        FieldSource<JavaClassSource> field = javaEntityImpl.addField().setPrivate().setType(fieldType).setName(fieldName);
        if (isStarProperty) {
            javaEntityImpl.addImport(LinkedHashMap.class);
            field.setLiteralInitializer("new LinkedHashMap<>()");
        }
    }
}
