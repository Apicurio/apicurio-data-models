package io.apicurio.umg.pipe;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import io.apicurio.umg.models.EntityModel;
import io.apicurio.umg.models.PropertyModel;

public class NormalizePropertiesStage extends AbstractStage {
	@Override
	protected void doProcess() {

		List<EntityModel> branchEntities = getState().getModelIndex().findEntities("").stream().filter(model -> !model.isLeaf()).collect(Collectors.toList());
		int changesMade;
		do {
			changesMade = 0;
			for (EntityModel parentEntity : branchEntities) {
				// Get all direct children of this parent entity.
				Collection<EntityModel> childEntities = getState().findChildEntitiesFor(parentEntity);
				// Get a collection of all properties for the children
				Set<PropertyModel> allProperties = new HashSet<>();
				childEntities.forEach(child -> allProperties.addAll(child.getProperties().values()));

				// Filter the full list of properties - only keep the properties that exist in *all* children.
				List<PropertyModel> propertiesToPullup = allProperties.stream()
						.filter(property -> childEntities.stream().map(c -> c.getProperties().containsKey(property.getName())).reduce(true, (sub, element) -> sub && element))
						.collect(Collectors.toList());

				// Now pull up each of the properties in the above list
				propertiesToPullup.forEach(property -> {
					parentEntity.addProperty(property);
					childEntities.forEach(childEntity -> childEntity.getProperties().remove(property.getName()));
				});

				// Did we find any properties to pull up?  If yes, increment "changes made".  We're going to keep
				// going through our model hierarchy until we've pulled up all the shared properties we can.
				changesMade += propertiesToPullup.size();
			}
		} while (changesMade > 0);
	}
}
