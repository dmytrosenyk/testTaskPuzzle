package com.senyk.puzzle;

import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class DropListener extends DropTargetAdapter {

  private final List<JPanel> solution;
  private final JPanel panel;

  private static final DataFlavor puzzleFlavor = new DataFlavor(Puzzle.class, "Puzzle");

  public DropListener(JPanel panel, List<JPanel> solution) {
    this.panel = panel;
    DropTarget dropTarget = new DropTarget(panel, DnDConstants.ACTION_COPY, this, true, null);
    this.solution=solution;
  }

  @Override
  public void drop(DropTargetDropEvent event) {
    try {
      DropTarget test = (DropTarget) event.getSource();
      Component ca = test.getComponent();
      ca.getMousePosition();
      Transferable tr = event.getTransferable();

      if (event.isDataFlavorSupported(puzzleFlavor)) {
        Component[] componentList = panel.getComponents();
        Puzzle transferData = (Puzzle) tr.getTransferData(puzzleFlavor);

        boolean f=true;
        for(Component c : componentList){
          if(c instanceof Puzzle){
            ((Puzzle) c).setIcon(transferData.getIcon());
            ((Puzzle) c).setNumber(transferData.getNumber());
            f=false;
          }
        }
        if(f){
          Puzzle puzzle=new Puzzle(transferData.getIcon(),transferData.getNumber());
          panel.add(puzzle);
        }
        panel.revalidate();
        panel.repaint();
        event.dropComplete(true);
        if (isSolved(solution)) {
          solution.forEach(x->x.setBackground(Color.GREEN));
          JOptionPane.showMessageDialog(panel, "You solved puzzles",
              "Congratulation", JOptionPane.INFORMATION_MESSAGE);
        }
      }
      else {
        event.rejectDrop();
      }
    } catch (Exception e) {
      e.printStackTrace();
      event.rejectDrop();
    }
  }

  private boolean isSolved(List<JPanel> panels){
    int l = panels.size();
    List<Integer> numberPosition = new ArrayList<>();
    for (int i = 0; i < l; i++) {
      numberPosition.add(i);
    }
    List<Integer> actualNumberPosition = new ArrayList<>();
    for (JPanel p:panels) {
      Component[] componentList = p.getComponents();
      for(Component c : componentList){
        if(c instanceof Puzzle){
          actualNumberPosition.add(((Puzzle) c).getNumber());
        }
      }
    }
    return numberPosition.equals(actualNumberPosition);
  }
}
