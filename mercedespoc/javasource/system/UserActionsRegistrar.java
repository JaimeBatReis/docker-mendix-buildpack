package system;

import com.mendix.core.actionmanagement.IActionRegistrator;

public class UserActionsRegistrar
{
  public void registerActions(IActionRegistrator registrator)
  {
    registrator.bundleComponentLoaded();
    registrator.registerUserAction(excelimporter.actions.GetHeaderInformationFromExcelFile.class);
    registrator.registerUserAction(excelimporter.actions.RefreshClass.class);
    registrator.registerUserAction(excelimporter.actions.StartImportByTemplate.class);
    registrator.registerUserAction(mxmodelreflection.actions.ReplaceToken.class);
    registrator.registerUserAction(mxmodelreflection.actions.SyncObjects.class);
    registrator.registerUserAction(mxmodelreflection.actions.TestThePattern.class);
    registrator.registerUserAction(mxmodelreflection.actions.ValidateTokensInMessage.class);
    registrator.registerUserAction(seleniummodule.actions.FetchFromAFAB.class);
    registrator.registerUserAction(seleniummodule.actions.SubmitIHKDraft.class);
    registrator.registerUserAction(system.actions.VerifyPassword.class);
  }
}
