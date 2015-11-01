package org.imogene.epicam.client.ui.editor.nested;

import java.util.ArrayList;
import java.util.List;

import org.imogene.epicam.client.i18n.NLS;
import org.imogene.epicam.shared.EpicamRequestFactory;
import org.imogene.epicam.shared.proxy.*;
import org.imogene.epicam.shared.request.CommandeRequest;

import org.imogene.web.client.i18n.BaseNLS;
import org.imogene.web.client.ui.field.ImogField;
import org.imogene.web.client.ui.field.group.FieldGroupPanel;
import org.imogene.web.client.util.ImogKeyGenerator;
import org.imogene.web.shared.proxy.GeoFieldProxy;
import org.imogene.web.shared.request.ImogEntityRequest;

import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor.Ignore;
import com.google.gwt.editor.client.EditorError;
import com.google.gwt.editor.client.IsEditor;
import com.google.gwt.editor.client.adapters.EditorSource;
import com.google.gwt.editor.client.adapters.ListEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * Editor that provides the UI components to manage the list of DetailCommandeIntrantEditorNestedRow
 * in the Commande editor
 * @author MEDES-IMPS
 */
public class CommandeIntrantsListEditor extends Composite
		implements
			IsEditor<ListEditor<DetailCommandeIntrantProxy, DetailCommandeIntrantEditorNestedRow>> {

	private static EditorUiBinder uiBinder = GWT.create(EditorUiBinder.class);

	interface EditorUiBinder
			extends
				UiBinder<Widget, CommandeIntrantsListEditor> {
	}

	protected final EpicamRequestFactory requestFactory;
	private DetailCommandeIntrantListEditorSource editorSource;
	private ListEditor<DetailCommandeIntrantProxy, DetailCommandeIntrantEditorNestedRow> editor;
	//private ListEditor<DetailCommandeIntrantProxy, DetailCommandeIntrantEditorNestedForm> editor;
	private ImogEntityRequest request;

	@UiField(provided = true)
	@Ignore
	FieldGroupPanel listSection;
	@UiField(provided = true)
	VerticalPanel listContainer;
	@UiField(provided = true)
	@com.google.gwt.editor.client.Editor.Ignore
	Image addItem;

	/* header row (field names) */
	@UiField
	@Ignore
	Label intrantLabel;
	@UiField
	@Ignore
	Label quantiteRequiseLabel;
	@UiField
	@Ignore
	Label quantiteEnStockLabel;

	public CommandeIntrantsListEditor(EpicamRequestFactory factory) {

		this.requestFactory = factory;
		editorSource = new DetailCommandeIntrantListEditorSource();
		editor = ListEditor.of(editorSource);

		listContainer = new VerticalPanel();
		addItem = new Image(GWT.getModuleBaseURL() + "images/relation_add.png");
		addItem.setTitle(BaseNLS.constants().button_add());

		listSection = new FieldGroupPanel();
		listSection.setGroupTitle(NLS.constants().commande_field_intrants());
		listSection.setLabelFontSize("12px");
		listSection.addStyleName("fieldGroup-ListEditor");

		initWidget(uiBinder.createAndBindUi(this));

		intrantLabel.setText(NLS.constants()
				.detailCommandeIntrant_field_intrant());
		quantiteRequiseLabel.setText(NLS.constants()
				.detailCommandeIntrant_field_quantiteRequise());
		quantiteEnStockLabel.setText(NLS.constants()
				.detailCommandeIntrant_field_quantiteEnStock());

	}

	/**
	 * Remove the DetailCommandeIntrant at the specified index
	 * @param index of the DetailCommandeIntrant
	 */
	private void remove(int index) {
		editor.getList().remove(index);
	}

	/**
	 * Get the DetailCommandeIntrant at the specified index
	 * @param index of the DetailCommandeIntrant
	 */
	private DetailCommandeIntrantProxy getProxy(int index) {
		return editor.getList().get(index);
	}

	@Override
	public ListEditor<DetailCommandeIntrantProxy, DetailCommandeIntrantEditorNestedRow> asEditor() {
		return editor;
	}

	@UiHandler("addItem")
	void onAddClick(ClickEvent event) {
		add();
	}

	/**
	 * Adds a new value to the editor list
	 * Prerequisite: Context must have been set through the SetRequestContext method
	 */
	private void add() {
		DetailCommandeIntrantProxy newDetailCommandeIntrant = request
				.create(DetailCommandeIntrantProxy.class);
		newDetailCommandeIntrant.setId(ImogKeyGenerator
				.generateKeyId("DET_CMD_INT"));
		newDetailCommandeIntrant.setVersion(0);
		//request.saveIntrants(newDetailCommandeIntrant, true);

		addValue(newDetailCommandeIntrant, true);
	}

	/**
	 * Adds a list of values to the editor list
	 */
	private void addValue(DetailCommandeIntrantProxy value, boolean isNew) {
		if (value != null) {
			if (editor.getList() == null)
				editor.setValue(new ArrayList<DetailCommandeIntrantProxy>());
			editor.getList().add(value);
			updateIndex();

			// update subeditor
			List<DetailCommandeIntrantEditorNestedRow> editors = editor
					.getEditors();
			DetailCommandeIntrantEditorNestedRow subEditor = editors
					.get(editors.size() - 1);
			subEditor.setNewProxy(isNew);
			subEditor.computeVisibility(null, true);
			subEditor.setEdited(true);
		}
	}

	public void up(DetailCommandeIntrantEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex > 0) {
			listContainer.insert(editor, currentIndex - 1);
			updateIndex();
		}
	}

	public void down(DetailCommandeIntrantEditorNestedRow editor) {
		int currentIndex = listContainer.getWidgetIndex(editor);
		if (currentIndex < listContainer.getWidgetCount() + 1) {
			listContainer.insert(editor, currentIndex + 2);
			updateIndex();
		}
	}

	private void updateIndex() {
		int count = listContainer.getWidgetCount();
		for (int i = 0; i < count; i++) {
			DetailCommandeIntrantEditorNestedRow subEditor = (DetailCommandeIntrantEditorNestedRow) listContainer
					.getWidget(i);
			subEditor.setIndex(i);
		}
	}

	public void setRequestContextForListEditors(ImogEntityRequest ctx) {
		this.request = ctx;
	}

	public void setEdited(boolean isEdited) {

		List<DetailCommandeIntrantEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeIntrantEditorNestedRow subEditor : editors)
				subEditor.setEdited(isEdited);
		}
		addItem.setVisible(isEdited);
	}

	public void computeVisibility(ImogField<?> source, boolean allValidation) {

		List<DetailCommandeIntrantEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeIntrantEditorNestedRow subEditor : editors)
				subEditor.computeVisibility(source, allValidation);
		}
	}

	/**
	 * Validate nested forms fields values
	 */
	public void validateFields() {

		List<DetailCommandeIntrantEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeIntrantEditorNestedRow subEditor : editors)
				subEditor.validateFields();
		}
	}

	/**
	 * Dispatch show errors to nested rows 
	 */
	public void showErrors(List<EditorError> errors) {
		List<DetailCommandeIntrantEditorNestedRow> editors = editor
				.getEditors();
		if (editors != null && editors.size() > 0) {
			for (DetailCommandeIntrantEditorNestedRow subEditor : editors)
				subEditor.showErrors(errors);
		}
	}

	/**
	 * Internal class
	 */
	private class DetailCommandeIntrantListEditorSource
			extends
				EditorSource<DetailCommandeIntrantEditorNestedRow> {

		@Override
		public DetailCommandeIntrantEditorNestedRow create(int index) {

			final DetailCommandeIntrantEditorNestedRow subEditor = new DetailCommandeIntrantEditorNestedRow(
					requestFactory);
			subEditor.setIndex(index);
			listContainer.insert(subEditor, index);

			subEditor.setDeleteClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					if (Window.confirm(BaseNLS.constants()
							.confirmation_delete())) {
						DetailCommandeIntrantProxy proxy = getProxy(subEditor
								.getIndex());
						if (!subEditor.isNewProxy())
							request.delete(proxy);
						remove(subEditor.getIndex());
						updateIndex();
					}
				}
			});
			return subEditor;
		}

		@Override
		public void dispose(DetailCommandeIntrantEditorNestedRow subEditor) {
			subEditor.removeFromParent();
		}

		@Override
		public void setIndex(DetailCommandeIntrantEditorNestedRow subEditor,
				int index) {
			listContainer.insert(subEditor, index);
		}
	}
}
