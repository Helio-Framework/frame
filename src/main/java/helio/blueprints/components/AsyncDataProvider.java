package helio.blueprints.components;

import java.io.InputStream;

import helio.blueprints.mappings.TranslationUnit;


/**
 * This components asynchronous {@link DataProvider}.
 * @author Andrea Cimmino Arriaga
 */
public interface AsyncDataProvider extends DataProvider {

	/**
	 * This method fetches the data from a source and invokes an internal {@link TranslationUnit} providing such data and triggering the translation task.
	 * @return always null
	 */
	@Override
	public InputStream getData();


	/**
	 * This method is expected to receive a {@link TranslationUnit} so it can be called asynchronously.
	 * @param translationUnit a suitable configuration
	 */
	public void setTranslationUnit(TranslationUnit translationUnit);




}
