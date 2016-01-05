/*
 * Copyright 2016 Red Hat, Inc. and/or its affiliates.
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *    http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.wirez.core.client.control.toolbox;

import com.google.gwt.user.client.ui.IsWidget;
import org.wirez.core.api.graph.Element;
import org.wirez.core.client.canvas.CanvasHandler;

public interface Toolbox<E extends Element> extends IsWidget {

    void initialize(CanvasHandler canvasHandler);

    void show(E element, double x, double y);

    void hide();
}