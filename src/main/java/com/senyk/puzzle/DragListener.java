package com.senyk.puzzle;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DragGestureEvent;
import java.awt.dnd.DragGestureListener;
import javax.swing.*;

public class DragListener implements DragGestureListener {

  private static final DataFlavor puzzleFlavor = new DataFlavor(Puzzle.class, "Puzzle");

  @Override
  public void dragGestureRecognized(DragGestureEvent event) {
    JLabel label = (JLabel) event.getComponent();

    Transferable transferable = new Transferable() {
      @Override
      public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{puzzleFlavor};
      }

      @Override
      public boolean isDataFlavorSupported(DataFlavor flavor) {

        return isDataFlavorSupported(flavor);
      }

      @Override
      public Object getTransferData(DataFlavor flavor) {
        return label;
      }
    };
    event.startDrag(null, transferable);
  }
}

