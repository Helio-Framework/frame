package helio.blueprints;

import io.reactivex.rxjava3.core.FlowableOnSubscribe;

/**
 * The {@link DataProvider} implementations provide fragments of data without taking the format of such data into account. The {@link DataProvider} implementations are meant to deal with the protocols and methods required to fetch the data from a source.<p>The {@link DataProvider} return data when invoked.
 * @author Andrea Cimmino Arriaga
 */
public interface DataProvider extends ConfigurableBlueprint,  FlowableOnSubscribe<String> {

	



}
