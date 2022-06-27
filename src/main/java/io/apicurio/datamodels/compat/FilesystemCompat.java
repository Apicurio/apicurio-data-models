/*
 * Copyright 2019 Red Hat
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
package io.apicurio.datamodels.compat;

import io.apicurio.datamodels.core.diff.ruleset.Ruleset;
import jsweet.lang.Async;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.Reader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class FilesystemCompat {
    @Async
    public static CompletableFuture<String> readFileSync(String filePath) {
        CompletableFuture<String> f = new CompletableFuture<>();
        InputStream is = null;
        try {
            is = new FileInputStream(filePath);
        } catch (FileNotFoundException e) {
            f.completeExceptionally(new FileNotFoundException("File not found: " + filePath));
        }
        int bufferSize = 1024;
        char[] buffer = new char[bufferSize];
        StringBuilder out = new StringBuilder();
        assert is != null;
        Reader in = new InputStreamReader(is, StandardCharsets.UTF_8);
        try {
            for (int numRead; (numRead = in.read(buffer, 0, buffer.length)) > 0; ) {
                out.append(buffer, 0, numRead);
            }
        } catch (Exception e) {
           LoggerCompat.info("Failed to load file..");
        }

        f.complete(out.toString());
        return f;
    }

    public static void getResource(Class<?> rulesetClass) {
        String nm = rulesetClass.getResource("oas").getPath();
        LoggerCompat.info(rulesetClass.getSimpleName());
    }
}
