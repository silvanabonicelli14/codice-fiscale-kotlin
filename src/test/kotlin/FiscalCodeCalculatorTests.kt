import com.cgm.codicefiscale.FiscalCodeCalculator
import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.helpers.Genre
import com.cgm.codicefiscale.services.SqLiteDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalStateException
import java.time.LocalDate

class FiscalCodeCalculatorTests {
    private var sut = FiscalCodeCalculator(SqLiteDataService())

    @ParameterizedTest(name = "Calculate CF function should return {1}")
    @MethodSource("encodeCfArguments")
    fun `Fiscal Code for Person Happy Path`(person: Person, expected: String) {
        Assertions.assertEquals(expected, sut.getFiscalCode(person))
    }

    @Test
    fun `Fiscal Code for Person with wrong Country of birth throws Exception`() {
        val person = Person("sasas","sasasas",Genre.F,LocalDate.parse("1977-05-01"),"sasas")
        val exception = assertThrows<IllegalStateException>{sut.getFiscalCode(person)}
        exception.message?.let { Assertions.assertTrue(it.contains("city of birth not founded")) }
    }

    companion object {
        @JvmStatic
        fun encodeCfArguments(): List<Arguments> =
            listOf(
                Arguments.of(
                    Person("Silvana", "Bonicelli", Genre.F, LocalDate.parse("1977-05-01"), "Breno"),
                    "BNCSVN77E41B149S"
                ),
                Arguments.of(
                    Person("Alessandro", "Fiorini", Genre.M, LocalDate.parse("1976-09-09"), "Lovere"),
                    "FRNLSN76P09E704H"
                )
            )
    }
}