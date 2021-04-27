import com.cgm.codicefiscale.services.CsvDataService
import com.cgm.codicefiscale.factories.DataServiceFactory
import com.cgm.codicefiscale.services.SqLiteDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.lang.IllegalStateException

class DataServiceFactoryTests {
    private var sut = DataServiceFactory()

    @Test
    fun `Get Data Service from existing system environment variable happy path`() {
        val result = sut.getDataServiceFromEnvironment()
        Assertions.assertTrue(result is CsvDataService)
    }
    @Test
    fun `Get Data Service from not existing system environment variable return default value csv`() {
        val result = sut.getDataServiceFromEnvironment("BLABLA")
        Assertions.assertTrue(result is CsvDataService)
    }

    @Test
    fun `Get Data Service from mode value manual return mode `() {
        val result = sut.getDataServiceByModeValue("csv")
        Assertions.assertTrue(result is CsvDataService)
    }

    @Test
    fun `Get Data Service from not valid mode value manual throw exception `() {
        assertThrows<IllegalStateException>{sut.getDataServiceByModeValue("blabla")}
    }

    @Test
    fun `Get Data Service without mode return default mode from environment `() {
        val result = sut.getDataService("")
        Assertions.assertTrue(result is CsvDataService)
    }

    @Test
    fun `Get Data Service with specific mode  mode return default mode from environment `() {
        val result = sut.getDataService("sqlite")
        Assertions.assertTrue(result is SqLiteDataService)
    }
}