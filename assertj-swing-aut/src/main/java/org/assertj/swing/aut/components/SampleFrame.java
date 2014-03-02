package org.assertj.swing.aut.components;

import javax.swing.JFrame;

import net.miginfocom.layout.AC;
import net.miginfocom.layout.LC;
import net.miginfocom.swing.MigLayout;

public class SampleFrame extends JFrame {

  private static final long serialVersionUID = 1L;

  public SampleFrame() {
    setTitle(getClass().getCanonicalName());
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }

  public void setMiglayout(LC layout, AC columns, AC rows) {
    setLayout(new MigLayout(layout, columns, rows));
  }

  public void setMiglayout() {
    setMiglayout(new LC(), new AC(), new AC());
  }

}
