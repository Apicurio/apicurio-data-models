/*
 * Copyright 2021 Red Hat Inc
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

package io.apicurio.datamodels._build;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.io.IOUtils;

/**
 * @author eric.wittmann@gmail.com
 */
public class TsImportOrganizer {

    public static void main(String[] args) throws Exception {
        System.out.println("------------------------------------------------");
        System.out.println("Organizing imports in all generated TS files.");
        System.out.println("------------------------------------------------");

        File srcDir = null;
        if (args.length < 1) {
            throw new RuntimeException("Missing argument: srcDir");
        }
        srcDir = new File(args[0]);
        if (!srcDir.isDirectory()) {
            throw new RuntimeException("Invalid argument: srcDir must be a directory");
        }

        Set<String> tsPaths = findTsPaths(srcDir);

        System.out.print("Processing TS files: [");
        tsPaths.forEach(path -> organizeTsImports(path));
        System.out.println("]");

        System.out.println("Successfully organized imports for " + tsPaths.size() + " ts files.");
    }

    /**
     * @param srcDir
     */
    private static Set<String> findTsPaths(File srcDir) {
        Set<String> paths = new TreeSet<String>();
        findTsPaths(srcDir, paths);
        return paths;
    }

    /**
     * @param srcDir
     * @param paths
     */
    private static void findTsPaths(File srcDir, Set<String> paths) {
        for (File file : srcDir.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".ts")) {
                String fullPath = file.getAbsolutePath();
                paths.add(fullPath);
            } else if (file.isDirectory()) {
                findTsPaths(file, paths);
            }
        }
    }

    /**
     * Organize imports for the TS file.
     * @param tsPath
     */
    private static void organizeTsImports(String tsPath) {
        System.out.print(".");

        File tsFile = new File(tsPath);
        if (!tsFile.isFile()) {
            throw new RuntimeException(tsPath + " is not a file.");
        }

        List<String> lines;
        try (FileInputStream in = new FileInputStream(tsFile)) {
            lines = IOUtils.readLines(in, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        List<String> allImports = lines.stream().filter(line -> line.startsWith("import")).collect(Collectors.toList());
        try (FileWriter writer = new FileWriter(tsFile)) {
            // First, write the comment.  Don't want to strip credit from JSweet. :)
            writer.write(lines.get(0));
            writer.write(System.lineSeparator());

            // Now write all the imports
            for (String line : allImports) {
                writer.write(line);
                writer.write(System.lineSeparator());
            }

            // Now write the rest of the content, skipping imports
            lines.remove(0);
            for (String line : lines) {
                if (!line.startsWith("import")) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
