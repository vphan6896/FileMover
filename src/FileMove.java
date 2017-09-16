import java.awt.dnd.*;
import java.awt.*;

import javax.swing.*;

import org.apache.commons.io.FileUtils;

import java.awt.datatransfer.*;
import java.io.*;
import java.util.Scanner;

class DragAndDrop extends JFrame {
	private JTextArea jt;

	public DragAndDrop() {
		createAndShowGUI();
	}

	private void createAndShowGUI() {
		setTitle("Drag and Drop Example");
		setSize(400, 400);
		setVisible(true);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Create JTextArea
		jt = new JTextArea();
		add(jt);

		enableDragAndDrop();

		setLocationRelativeTo(null);
	}

	private void enableDragAndDrop() {
		DropTarget target = new DropTarget(jt, new DropTargetListener() {
			public void dragEnter(DropTargetDragEvent e) {
			}

			public void dragExit(DropTargetEvent e) {
			}

			public void dragOver(DropTargetDragEvent e) {
			}

			public void dropActionChanged(DropTargetDragEvent e) {

			}

			public void drop(DropTargetDropEvent e) {
				try {
					// Accept the drop first, important!
					e.acceptDrop(DnDConstants.ACTION_COPY_OR_MOVE);

					// Get the files that are dropped as java.util.List
					java.util.List list = (java.util.List) e.getTransferable()
							.getTransferData(DataFlavor.javaFileListFlavor);

					// Now get the first file from the list,
					File srcFile = (File) list.get(0);
					jt.setText("C:\\Users\\pokec\\Desktop\\");
					File destDir = new File("C:\\Users\\pokec\\Desktop\\"
							);
					FileUtils.copyFileToDirectory(srcFile, destDir);

				} catch (Exception ex) {
				}
			}
		});
	}

	static public void main(String args[]) {
		new DragAndDrop();
	}
}