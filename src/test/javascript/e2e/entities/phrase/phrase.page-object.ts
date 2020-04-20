import { element, by, ElementFinder } from 'protractor';

export class PhraseComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-phrase div table .btn-danger'));
  title = element.all(by.css('jhi-phrase div h2#page-heading span')).first();

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class PhraseUpdatePage {
  pageTitle = element(by.id('jhi-phrase-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));
  authorInput = element(by.id('field_author'));
  textInput = element(by.id('field_text'));
  referenseInput = element(by.id('field_referense'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAuthorInput(author: string): Promise<void> {
    await this.authorInput.sendKeys(author);
  }

  async getAuthorInput(): Promise<string> {
    return await this.authorInput.getAttribute('value');
  }

  async setTextInput(text: string): Promise<void> {
    await this.textInput.sendKeys(text);
  }

  async getTextInput(): Promise<string> {
    return await this.textInput.getAttribute('value');
  }

  async setReferenseInput(referense: string): Promise<void> {
    await this.referenseInput.sendKeys(referense);
  }

  async getReferenseInput(): Promise<string> {
    return await this.referenseInput.getAttribute('value');
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class PhraseDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-phrase-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-phrase'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
