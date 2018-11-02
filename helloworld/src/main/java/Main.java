/*
 * Copyright 2018 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

import com.google.cloud.tools.jib.api.Containerizer;
import com.google.cloud.tools.jib.api.DockerDaemonImage;
import com.google.cloud.tools.jib.api.Jib;
import com.google.cloud.tools.jib.configuration.CacheDirectoryCreationException;
import com.google.cloud.tools.jib.filesystem.AbsoluteUnixPath;
import com.google.cloud.tools.jib.image.InvalidImageReferenceException;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ExecutionException;

public class Main {

  public static void main(String[] args) throws InvalidImageReferenceException, IOException, InterruptedException, ExecutionException, CacheDirectoryCreationException {

    Jib.from("busybox")
        .addLayer(Collections.singletonList(Paths.get("helloworld.sh")), AbsoluteUnixPath.get("/"))
        .setEntrypoint("/bin/sh", "/helloworld.sh")
        .containerize(
            Containerizer.to(DockerDaemonImage.named("testjibcore")));

    System.out.println("DONE");
  }
}
