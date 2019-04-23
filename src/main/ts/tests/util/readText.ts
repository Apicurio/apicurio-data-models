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


export function readText(url: string): string {
    url = readText.base + url;

    let xhr: XMLHttpRequest = new XMLHttpRequest();
    let text: string = null;

    xhr.open("GET", url, false);

    xhr.onload = function (e) {
        if (xhr.status === 200) {
            text = xhr.responseText;
        } else {
            console.error('readText', url, xhr.statusText);
        }
    };

    xhr.onerror = function (e) {
        console.error('readText', url, xhr.statusText);
    };

    xhr.send(null);
    return text;
}

readText.base = '/base/';
