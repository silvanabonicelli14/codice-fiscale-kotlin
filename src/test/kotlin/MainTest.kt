import com.cgm.codicefiscale.FiscalCodeCalculator
import com.cgm.codicefiscale.Person
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException


class MainTest {
    var fiscalCodeCalculator = FiscalCodeCalculator()

    @Test
    fun `Fiscal Code for Person with empty name`() {
        var person = Person("","","","","")
        val exception = assertThrows<IllegalArgumentException>{fiscalCodeCalculator.getFiscalCode(person)}
        //Assertions.assertEquals(expectedException, exception.message)

    }
    @ParameterizedTest(name = "encodeLastName function should return {1} for {0}")
    @MethodSource("encodeLastNameArguments")
    fun `Encode lastname `() {
        Assertions.assertEquals("VTI", fiscalCodeCalculator.encodeLastName("Vito"))
    }

    @ParameterizedTest(name = "encodeFirstName function should return {1} for {0}")
    @MethodSource("encodeFirstNameArguments")
    fun `Encode firstname `() {
        Assertions.assertEquals("LCA", fiscalCodeCalculator.encodeFirstName("Alice"))
    }

    companion object {
        @JvmStatic
        fun encodeLastNameArguments(): List<Arguments> =
            listOf(
                Arguments.of("Vito", "VTI"),
                Arguments.of("v", "XXX"),
                Arguments.of("vit", "vti")
            )
        @JvmStatic
        fun encodeFirstNameArguments(): List<Arguments> =
            listOf(
                Arguments.of("Alice", "LCA"),
                Arguments.of("Silvana", "SVN")
            )
    }

}