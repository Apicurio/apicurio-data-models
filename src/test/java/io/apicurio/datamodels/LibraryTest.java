/*
 * Copyright 2019 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.apicurio.datamodels;

import io.apicurio.datamodels.core.models.Node;
import io.apicurio.datamodels.core.models.NodePath;
import io.apicurio.datamodels.core.models.ValidationProblem;
import io.apicurio.datamodels.core.models.ValidationProblemSeverity;
import io.apicurio.datamodels.core.validation.IValidateDocumentExtension;
import org.junit.Assert;
import org.junit.Test;

import io.apicurio.datamodels.core.models.Document;
import io.apicurio.datamodels.core.models.DocumentType;
import io.apicurio.datamodels.openapi.v3.models.Oas30Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;

/**
 * Tests for the {@link Library} class.
 * @author eric.wittmann@gmail.com
 */
public class LibraryTest {
    
    @Test
    public void testCreateAndCloneDocument() {
        DocumentType[] values = DocumentType.values();
        for (DocumentType dt : values) {
            doCreateAndClone(dt);
        }
    }

    private void doCreateAndClone(DocumentType type) {
        Document document = Library.createDocument(type);
        Assert.assertNotNull(document);
        Assert.assertEquals(type, document.getDocumentType());
        Document clone = Library.cloneDocument(document);
        Assert.assertNotNull(clone);
        Assert.assertEquals(type, clone.getDocumentType());
        Assert.assertEquals(document.getDocumentType(), clone.getDocumentType());
        Assert.assertFalse(document == clone);
    }
    
    @Test
    public void testReadDocumentFromJSONString() {
        String jsonString = "{\r\n" + 
                "    \"openapi\": \"3.0.2\",\r\n" + 
                "    \"info\": {\r\n" + 
                "        \"title\": \"Very Simple API\",\r\n" + 
                "        \"version\": \"1.0.0\"\r\n" + 
                "    }\r\n" + 
                "}";
        Document doc = Library.readDocumentFromJSONString(jsonString);
        Assert.assertEquals(DocumentType.openapi3, doc.getDocumentType());
        Assert.assertEquals("3.0.2", ((Oas30Document) doc).openapi);
    }

    @Test
    public void testValidate() {
        String jsonString = "{\r\n" +
                "    \"openapi\": \"3.0.9\",\r\n" +
                "    \"info\": {\r\n" +
                "        \"title\": \"Very Simple API\",\r\n" +
                "        \"version\": \"1.0.0\"\r\n" +
                "    }\r\n" +
                "}";
        Document doc = Library.readDocumentFromJSONString(jsonString);

        List<ValidationProblem> problems = Library.validate(doc, null);
        Assert.assertTrue(problems.size() > 0);
    }

    private final CountDownLatch lock = new CountDownLatch(1);

    @Test
    public void testValidateDocument() {
        String jsonString = "{\r\n" +
                "    \"openapi\": \"3.0.2\",\r\n" +
                "    \"info\": {\r\n" +
                "        \"title\": \"Very Simple API\",\r\n" +
                "        \"version\": \"1.0.0\"\r\n" +
                "    }\r\n" +
                "}";
        Document doc = Library.readDocumentFromJSONString(jsonString);

        List<IValidateDocumentExtension> extensions = new ArrayList<>();
        extensions.add(new TestValidationExtension());

        CompletableFuture<List<ValidationProblem>> problems = Library.validateDocument(doc, null, extensions);
        List<ValidationProblem> receivedProblems = new ArrayList<>();
        problems.whenCompleteAsync((pList, done) -> {
            receivedProblems.addAll(new ArrayList<>(pList));
            lock.countDown();
        });
        Optional<ValidationProblem> extensionProblem = receivedProblems.stream().
                filter(p -> Objects.equals(p.errorCode, "TEST-001")).
                findFirst();
        Assert.assertTrue(extensionProblem.isPresent());
    }
}

class TestValidationExtension implements IValidateDocumentExtension {

    @Override
    public CompletableFuture<List<ValidationProblem>> validateDocument(Node document) {
        List<ValidationProblem> fakeProblems = new ArrayList<>();
        fakeProblems.add(new ValidationProblem(
                "TEST-001", new NodePath("testpath"), "test", "Test problem", ValidationProblemSeverity.low
        ));

        return CompletableFuture.completedFuture(fakeProblems);
    }
}