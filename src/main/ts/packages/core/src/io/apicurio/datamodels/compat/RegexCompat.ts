/**
 * @license
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

export class RegexCompat {
    
    public static matches(value: string, regex: string): boolean {
        let matchRegex: RegExp = new RegExp(regex);
        return matchRegex.test(value);
    }
    
    /**
     * Finds all strings within the given input that match the given regular expression.  Returns
     * all matches, with each match represented as a list of the groups specified in the regex.
     * @param value
     * @param regex
     */
    public static findMatches(value: string, regex: string): string[][] {
        let rval: string[][] = [];
        let matchRegex: RegExp = new RegExp(regex, 'g');
        let match: string[] = matchRegex.exec(value);
        while (match) {
            rval.push(match);
            match = matchRegex.exec(value);
        }
        return rval;
    }

}
