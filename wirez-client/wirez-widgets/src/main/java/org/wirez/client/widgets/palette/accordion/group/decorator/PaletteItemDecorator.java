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

package org.wirez.client.widgets.palette.accordion.group.decorator;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.IPrimitive;
import com.ait.lienzo.client.core.shape.Rectangle;

public interface PaletteItemDecorator  {

    PaletteItemDecorator setPadding(double padding);

    PaletteItemDecorator setCallback(Callback callback);
            
    IPrimitive<?> build(IPrimitive<?> item, double width, double height);
    
    interface Callback {
        
        void onShow(double x, double y);
        
        void onHide();
        
    }
    
}