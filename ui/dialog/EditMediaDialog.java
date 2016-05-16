package ui.dialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.JSpinner.DateEditor;  
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.JFormattedTextField;
import javax.swing.BoxLayout;
import javax.swing.JLabel;

import list.Album;
import mediadata.MediaData;
import model.table.MediaTableModel;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public final class EditMediaDialog  
                                       implements ActionListener     {	
    protected JDialog fEditMediaDialog;
	protected JButton fChangeReplaceMediaButton;
	protected JButton fAddTagButton;
	protected JButton fRemoveTagButton;
	private  JTextField fTextBoxEditFileName;
	private  JTextField fTextBoxEditMediaDescription; 
    private  JSpinner fSpinnerEditMonth;
    private  JSpinner fSpinnerEditDay;
    private  JSpinner fSpinnerEditYear;
    private  JComboBox<String> fComboBoxEditMediaTags;
    private Album fEditAlbum;
    private DefaultComboBoxModel<String> fModelTags;
    final private int fCopyIndexOfOriginal;
    final private MediaData fCopyMediaData;
    private List<String> fTagList;
    private String[] fTagListString;
    final protected MediaTableModel fCopyTableModel;
    private String fEditFileNameTrunc;    
    
		public EditMediaDialog(JFrame aParent, Album aCurrentAlbum, int aIndexOfOriginal,  MediaData aOrigMediaData, boolean aModal, MediaTableModel aTableModelCurrentAlbum)		{
		    fEditAlbum = aCurrentAlbum;
		    fCopyTableModel = aTableModelCurrentAlbum;
			fCopyIndexOfOriginal = aIndexOfOriginal;
			fCopyMediaData = aOrigMediaData;
		    
		    fChangeReplaceMediaButton = makeButton("OK", "Replace Media", this, "Completes Edit Of Media");
		    final JPanel panelOK = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		    panelOK.add(fChangeReplaceMediaButton);
		    		    
		    final JPanel mainPanel = new JPanel(new BorderLayout());
		    mainPanel.add(makeEditInfoPanel(), BorderLayout.PAGE_START);
		    mainPanel.add(panelOK, BorderLayout.PAGE_END);
		    
		    fEditMediaDialog = new JDialog(aParent, aModal);
		    fEditMediaDialog.getContentPane().add(mainPanel);
		    
		    fEditMediaDialog.setSize(500, 200);
		    fEditMediaDialog.setLocationRelativeTo(null);
		    fEditMediaDialog.pack();
		    fEditMediaDialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		    fEditMediaDialog.setTitle("Edit Media: " + fCopyMediaData.getFileName());
		    ImageIcon imgDialog = new ImageIcon(getClass().getResource("/resources/filecabinet.png")); 
		    fEditMediaDialog.setIconImage(imgDialog.getImage());
		    fEditMediaDialog.setVisible(true);
		}		
		
		@Override
		public final void actionPerformed(ActionEvent e)		{
		   
			if (e.getActionCommand().equals("Replace Media"))		    {
				final String editFileNameCheckDuplicate = new String(fTextBoxEditFileName.getText().trim().concat(".jpg"));
			    	
			    	    fCopyMediaData.setFileName(editFileNameCheckDuplicate);
			    	    fCopyMediaData.setDescription(fTextBoxEditMediaDescription.getText().trim());
			    	    fCopyMediaData.setDate(new SimpleDateFormat("MM").format( (Date)fSpinnerEditMonth.getValue() ).toString() + "/" + 
				                                new SimpleDateFormat("dd").format( (Date)fSpinnerEditDay.getValue() ).toString() + "/" + 
				                                new SimpleDateFormat("yyyy").format((Date)fSpinnerEditYear.getValue()).toString() );
				                       
			    	    fCopyMediaData.addTags(fTagList);
				        fEditAlbum.updateAlbum(fCopyMediaData, fCopyIndexOfOriginal);
				         
				        fEditMediaDialog.dispose();
		    }	else if(e.getActionCommand().equals("Add Tag"))		    {
		    		final String comboBoxTagAdd = new String(fComboBoxEditMediaTags.getEditor().getItem().toString().trim());
			    	
			    	if(noDuplicateTag(comboBoxTagAdd))		    	{
			    		fModelTags.addElement(comboBoxTagAdd);
				        fTagList.add(comboBoxTagAdd);	
			    	}  else	  {
			    		JOptionPane.showMessageDialog(fEditMediaDialog, "The Tag " + comboBoxTagAdd + "Is Already Used, Please Enter Another Tag", "Error Edit", JOptionPane.ERROR_MESSAGE);
			    	}
			    	
			    	( (JTextField)fComboBoxEditMediaTags.getEditor().getEditorComponent()).setText("");
		   	}	else if(e.getActionCommand().equals("Remove Tag"))	  {
		   		    final int selectedTagIndexRemove = fComboBoxEditMediaTags.getSelectedIndex(); 
			    	
			    	fModelTags.removeElementAt(selectedTagIndexRemove);
			        fTagList.remove(selectedTagIndexRemove);
		    }	else if (e.getActionCommand().equals("Cancel"))	 {
		    	fEditMediaDialog.dispose();
		    }
		}
		
		private final boolean noDuplicateTag(String aCheckComboBoxTagAdd)		{			
			boolean noDuplicateTagsFound = true;
			
			for(int x = 0; (x < fModelTags.getSize()) && (noDuplicateTagsFound); x++)			{
				if(aCheckComboBoxTagAdd.equalsIgnoreCase(fModelTags.getElementAt(x).toString()))	   {
				    noDuplicateTagsFound = false;
				}
			}			
			return noDuplicateTagsFound;
		}
		
		private final JPanel makeEditInfoPanel()		{
			final JPanel eip = new JPanel();
			
			eip.setLayout(new BoxLayout(eip, BoxLayout.Y_AXIS));
			eip.setBorder(BorderFactory.createTitledBorder(new EtchedBorder(EtchedBorder.LOWERED), "Information", TitledBorder.LEFT, TitledBorder.TOP, new Font("Serif", Font.BOLD, 12)));
		    		    
		    fEditFileNameTrunc = fCopyMediaData.getFileName().substring(0, fCopyMediaData.getFileName().lastIndexOf(".jpg"));
		    fTextBoxEditFileName = new JTextField(fEditFileNameTrunc, 24);
		    fTextBoxEditFileName.setEnabled(false);
		    final JPanel panelEditFileName = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
		    final JLabel editLabelName = new JLabel("Name:");
		    editLabelName.setFont(new Font("Serif", Font.BOLD,14));
		    panelEditFileName.add(editLabelName);
		    panelEditFileName.add(fTextBoxEditFileName);
		    eip.add(panelEditFileName);
		    
		    fTextBoxEditMediaDescription = new JTextField(fCopyMediaData.getDescription(), 31);
		    fTextBoxEditMediaDescription.setEditable(true);
		    fTextBoxEditMediaDescription.setEnabled(true);
		    final JPanel panelEditDescription = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
		    final JLabel  editLabelDescription = new JLabel("Description");
		    editLabelDescription.setFont(new Font("Serif", Font.BOLD, 14));
		    panelEditDescription.add(editLabelDescription);
		    panelEditDescription.add(fTextBoxEditMediaDescription);
		    eip.add(panelEditDescription);
		    
		    Date editMediaDate = null;
		    
		    try		    {
		       editMediaDate = (new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH).parse( fCopyMediaData.getDate()));
		    }	catch(ParseException pe)	{
		    	pe.printStackTrace();
		    }
		    
		    final JPanel spinnerPanelMain = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
		    JLabel editDateLabel = new JLabel("Date:");
		    editDateLabel.setFont(new Font("Serif", Font.BOLD,14));
		    spinnerPanelMain.add(editDateLabel);
		    JPanel spinnerPanelMonth = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		    spinnerPanelMonth.add(new JLabel("Month"));
		    fSpinnerEditMonth = createDateSpinner(Calendar.MONTH, "MM", editMediaDate, spinnerPanelMonth); 
		    spinnerPanelMain.add(spinnerPanelMonth);
		    JPanel spinnerPanelDay = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		    spinnerPanelDay.add(new JLabel("Day"));
		    fSpinnerEditDay = createDateSpinner(Calendar.DAY_OF_MONTH, "dd",editMediaDate, spinnerPanelDay);
		    spinnerPanelMain.add(spinnerPanelDay);
		    JPanel spinnerPanelYear = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
		    spinnerPanelYear.add(new JLabel("Year"));
		    fSpinnerEditYear = createDateSpinner(Calendar.YEAR, "yyyy", editMediaDate, spinnerPanelYear);
		    spinnerPanelMain.add(spinnerPanelYear);
		    eip.add(spinnerPanelMain);
		    
		    final JPanel panelEditTags = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
		    final JLabel editTagsLabel = new JLabel("Tags:");
		    editTagsLabel.setFont(new Font("Serif", Font.BOLD, 14));
		    panelEditTags.add(editTagsLabel);
		    		    
		    fTagList = fCopyMediaData.getTags();
		    fTagListString = fTagList.toArray(new String[fTagList.size()]);
		    fModelTags = new DefaultComboBoxModel<String>(fTagListString);
		    
		    fComboBoxEditMediaTags = new JComboBox<String>(fModelTags);
		    fComboBoxEditMediaTags.setPreferredSize(new Dimension(150, 20));
		    fComboBoxEditMediaTags.setEditable(true);
		    fComboBoxEditMediaTags.setActionCommand("tags");
		    fComboBoxEditMediaTags.addActionListener(this);
		    
		    panelEditTags.add(fComboBoxEditMediaTags);		    
		    fAddTagButton = makeButton("Add", "Add Tag", this, "Adds Tag Entered In ComboBox");
		    fRemoveTagButton = makeButton("Remove", "Remove Tag", this, "Removes Selected Tag in ComboBox");
		    panelEditTags.add(fAddTagButton);
		    panelEditTags.add(fRemoveTagButton);		    
		    eip.add(panelEditTags);
		    
		    return eip;
		}
		
		private final JButton makeButton(String aLabel, String aActionCommand, ActionListener aAl, String aToolTipText)		{
			JButton b = new JButton(aLabel);
			b.setPreferredSize(new Dimension(80, 20));
			b.setActionCommand(aActionCommand);
		    b.addActionListener(this);
		    b.setToolTipText(aToolTipText);
		    
		    return b;
		}
		
		private final JSpinner createDateSpinner(int calendarField, String datePart, Date editDate, JPanel spinnerPanel)		{
			SpinnerDateModel model = new SpinnerDateModel(editDate, null, null, calendarField);
			final JSpinner spinnersEditDate = new JSpinner(model);
			spinnersEditDate.setEditor(new DateEditor(spinnersEditDate, datePart));
			JFormattedTextField tf = ((JSpinner.DefaultEditor)spinnersEditDate.getEditor()).getTextField();
			tf.setEditable(false);
			
			spinnerPanel.add(spinnersEditDate);
			
			return spinnersEditDate;
		}
}