package org.wirez.core.client.components.palette;

import org.wirez.core.client.components.palette.model.HasPaletteItems;
import org.wirez.core.client.components.palette.model.PaletteDefinition;
import org.wirez.core.client.components.palette.view.PaletteView;

public interface Palette<I extends HasPaletteItems> {

    interface CloseCallback {

        boolean onClose();

    }

    interface ItemHoverCallback {

        boolean onItemHover( int pos, double mouseX, double mouseY, double itemX, double itemY );

    }

    interface ItemOutCallback {

        boolean onItemOut( int pos );

    }

    interface ItemMouseDownCallback {

        boolean onItemMouseDown( int pos, double mouseX, double mouseY, double itemX, double itemY );

    }

    interface ItemClickCallback {

        boolean onItemClick( int pos, double mouseX, double mouseY, double itemX, double itemY );

    }

    Palette<I> onItemHover( ItemHoverCallback callback );

    Palette<I> onItemOut( ItemOutCallback callback );

    Palette<I> onItemMouseDown( ItemMouseDownCallback callback );

    Palette<I> onItemClick( ItemClickCallback callback );

    Palette<I> onClose( CloseCallback callback );

    Palette<I> bind( I paletteDefinition );

    I getDefinition();

    void destroy();

}
