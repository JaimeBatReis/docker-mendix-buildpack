// This file was generated by Mendix Studio Pro.
//
// WARNING: Code you write here will be lost the next time you deploy the project.

package seleniummodule.proxies.microflows;

import java.util.HashMap;
import java.util.Map;
import com.mendix.core.Core;
import com.mendix.systemwideinterfaces.core.IContext;

public class Microflows
{
	/**
	 * @deprecated
	 * The default constructor of the Microflows class should not be used.
	 * Use the static microflow invocation methods instead.
	 */
	@java.lang.Deprecated(since = "9.12", forRemoval = true)
	public Microflows() {}

	// These are the microflows for the SeleniumModule module
	public static boolean aCT_FetchFromAFAB(IContext context, mercedespoc.proxies.COO _cOO)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		params.put("COO", _cOO == null ? null : _cOO.getMendixObject());
		return (java.lang.Boolean) Core.microflowCall("SeleniumModule.ACT_FetchFromAFAB").withParams(params).execute(context);
	}
	public static boolean aCT_SubmitIHKDraft(IContext context, mercedespoc.proxies.COO _cOO)
	{
		Map<java.lang.String, Object> params = new HashMap<>();
		params.put("COO", _cOO == null ? null : _cOO.getMendixObject());
		return (java.lang.Boolean) Core.microflowCall("SeleniumModule.ACT_SubmitIHKDraft").withParams(params).execute(context);
	}
}
