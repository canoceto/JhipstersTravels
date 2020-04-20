import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { PhraseComponentsPage, PhraseDeleteDialog, PhraseUpdatePage } from './phrase.page-object';

const expect = chai.expect;

describe('Phrase e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let phraseComponentsPage: PhraseComponentsPage;
  let phraseUpdatePage: PhraseUpdatePage;
  let phraseDeleteDialog: PhraseDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Phrases', async () => {
    await navBarPage.goToEntity('phrase');
    phraseComponentsPage = new PhraseComponentsPage();
    await browser.wait(ec.visibilityOf(phraseComponentsPage.title), 5000);
    expect(await phraseComponentsPage.getTitle()).to.eq('springBootDemoApp.phrase.home.title');
  });

  it('should load create Phrase page', async () => {
    await phraseComponentsPage.clickOnCreateButton();
    phraseUpdatePage = new PhraseUpdatePage();
    expect(await phraseUpdatePage.getPageTitle()).to.eq('springBootDemoApp.phrase.home.createOrEditLabel');
    await phraseUpdatePage.cancel();
  });

  it('should create and save Phrases', async () => {
    const nbButtonsBeforeCreate = await phraseComponentsPage.countDeleteButtons();

    await phraseComponentsPage.clickOnCreateButton();
    await promise.all([
      phraseUpdatePage.setAuthorInput('author'),
      phraseUpdatePage.setTextInput('text'),
      phraseUpdatePage.setReferenseInput('referense')
    ]);
    expect(await phraseUpdatePage.getAuthorInput()).to.eq('author', 'Expected Author value to be equals to author');
    expect(await phraseUpdatePage.getTextInput()).to.eq('text', 'Expected Text value to be equals to text');
    expect(await phraseUpdatePage.getReferenseInput()).to.eq('referense', 'Expected Referense value to be equals to referense');
    await phraseUpdatePage.save();
    expect(await phraseUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await phraseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Phrase', async () => {
    const nbButtonsBeforeDelete = await phraseComponentsPage.countDeleteButtons();
    await phraseComponentsPage.clickOnLastDeleteButton();

    phraseDeleteDialog = new PhraseDeleteDialog();
    expect(await phraseDeleteDialog.getDialogTitle()).to.eq('springBootDemoApp.phrase.delete.question');
    await phraseDeleteDialog.clickOnConfirmButton();

    expect(await phraseComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
