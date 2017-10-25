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

		// Create JTextArea (Box that shows what's happening
		jt = new JTextArea();
		add(jt);

		enableDragAndDrop();

		setLocationRelativeTo(null);
	}

	private void enableDragAndDrop() {
		DropTarget target = new DropTarget(jt, new DropTargetListener()
		{
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
					
					JTextField txtInput = new JTextField("");
					String input = JOptionPane.showInputDialog(
                            null, "Where to put file? (Root: Desktop)");
					
					//Root of directory for operations
					String root = "C:\\Users\\pokec\\Desktop\\";
					
					//find appropriate directory
					String directory = FindDirectory(input, root);
					
					//Show text in main window where file will go
					jt.setText(directory);
					
					File destDir = new File(directory);
					
					FileUtils.copyFileToDirectory(srcFile, destDir);
					jt.append("\n File has been copied.");

				} catch (Exception ex) {
					jt.setText("Something is wrong.");
					System.out.println("Something is wrong.");
				}
			}
		});
	}
	
	static public String FindDirectory(String input, String root) {
		
		File rootDir = new File("C:\\Users\\pokec\\Desktop\\");
		
		File[] directories = GetDirectories(rootDir);
		
		return null;
	}
	
	//Filter out all the files and get only directories
	static public File[] GetDirectories(File rootDir) {
		File[] directories = rootDir.listFiles();
		
		FileFilter fileFilter = new FileFilter() {
	         public boolean accept(File file) {
	            return file.isDirectory();
	         }
	    };
	    directories = rootDir.listFiles(fileFilter);
	    return directories;
	}

	static public void main(String args[]) {
		new DragAndDrop();
	}
}