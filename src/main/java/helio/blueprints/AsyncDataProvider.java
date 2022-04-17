package helio.blueprints;


/**
 * The {@link AsyncDataProvider} implementations provide fragments of data asynchronously without taking the format of such data into account. The {@link AsyncDataProvider} implementations are meant to deal with the protocols and methods required to fetch the data from a source.
 * <p>
 * On the contrary than {@link DataProvider} the {@link AsyncDataProvider} providers push data independently from when such data is requested.
 *
 * @author Andrea Cimmino Arriaga
 */
public interface AsyncDataProvider extends DataProvider {

	



}
