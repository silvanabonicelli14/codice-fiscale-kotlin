import com.cgm.codicefiscale.FiscalCodeCalculator
import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.services.SqLiteDataService
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.lang.IllegalArgumentException
import java.lang.IllegalStateException
import java.time.LocalDate


class MainTest {
    private var sut = FiscalCodeCalculator(SqLiteDataService())

    @ParameterizedTest(name = "encodeLastName function should return {1} for {0}")
    @MethodSource("wrongPersonArguments")
    fun `Fiscal Code for Person with empty field throws Exception`(person: Person, expectedMessage:String) {
        val exception = assertThrows<IllegalArgumentException>{sut.getFiscalCode(person)}
        Assertions.assertEquals(expectedMessage, exception.message)
    }

    @Test
    fun `Fiscal Code for Person with wrong date of birth throws Exception`() {
        val person = Person("sasas","sasasas","sasasa","1253648","sasas")
        assertThrows<IllegalStateException>{sut.getFiscalCode(person)}
    }

    @ParameterizedTest(name = "encodeLastName function should return {1} for {0}")
    @MethodSource("encodeLastNameArguments")
    fun `Encode lastname `(lastName:String,expected: String) {
        Assertions.assertEquals(expected, sut.encodedLastName(lastName))
    }

    @ParameterizedTest(name = "encodeFirstName function should return {1} for {0}")
    @MethodSource("encodeFirstNameArguments")
    fun `Encode firstname `(firstName:String,expected: String ) {
        Assertions.assertEquals(expected, sut.encodedFirstName(firstName))
    }

    @ParameterizedTest(name = "encodeYear function should return {1} for {0}")
    @MethodSource("encodeYearArguments")
    fun `Encode year `(dateOfBirth:LocalDate,expected: String ) {
        Assertions.assertEquals(expected, sut.encodedYearOfBirth(dateOfBirth))
    }

    @ParameterizedTest(name = "encodeMonth function should return {1} for {0}")
    @MethodSource("encodeMonthArguments")
    fun `Encode month `(dateOfBirth:LocalDate,expected: String ) {
        Assertions.assertEquals(expected, sut.encodedMonthOfBirth(dateOfBirth))
    }

    @ParameterizedTest(name = "encodeDay function should return {1} for {0}")
    @MethodSource("encodeDayArguments")
    fun `Encode day `(dateOfBirth:LocalDate,sex: String, expected: String ) {
        Assertions.assertEquals(expected, sut.encodedDayOfBirth(dateOfBirth, sex))
    }
    @ParameterizedTest(name = "encodeCity function should return {1} for {0}")
    @MethodSource("encodeCityArguments")
    fun `Encode valid city `(cityOfBirth:String, expected: String ) {
        Assertions.assertEquals(expected, sut.encodedCityOfBirth(cityOfBirth))
    }
   @Test
    fun `Encode not valid or not found  city  throws Exception`() {
       assertThrows<IllegalStateException>{sut.encodedCityOfBirth("dsd<as<s")}
    }

    @ParameterizedTest(name = "Calculate CF function should return {1}")
    @MethodSource("encodeCfArguments")
    fun `Fiscal Code for Person`(person: Person, expected: String) {
        Assertions.assertEquals(expected, sut.getFiscalCode(person))
    }

    @Test
    fun `test check digit for fiscal code`(){
        Assertions.assertEquals("S", sut.checkDigit("BNCSVN77E41B149"))
    }


    companion object {
        @JvmStatic
        fun encodeLastNameArguments(): List<Arguments> =
            listOf(
                Arguments.of("Vito", "VTI"),
                Arguments.of("v", "VXX"),
                Arguments.of("vit", "VTI"),
                Arguments.of("1", "XXX")
            )
        @JvmStatic
        fun encodeFirstNameArguments(): List<Arguments> =
            listOf(
                Arguments.of("Alice", "LCA"),
                Arguments.of("Silvana", "SVN"),
                Arguments.of("A", "AXX"),
                Arguments.of("1", "XXX")
            )
        @JvmStatic
        fun encodeYearArguments(): List<Arguments> =
            listOf(
                Arguments.of(LocalDate.parse("1977-01-05"), "77")
            )
        @JvmStatic
        fun encodeMonthArguments(): List<Arguments> =
            listOf(
                Arguments.of(LocalDate.parse("1977-05-01"), "E"),
                Arguments.of(LocalDate.parse("1977-01-01"), "A"),
                Arguments.of(LocalDate.parse("1977-12-01"), "T")
            )
        @JvmStatic
        fun encodeDayArguments(): List<Arguments> =
            listOf(
                Arguments.of(LocalDate.parse("1977-05-01"), "F","41"),
                Arguments.of(LocalDate.parse("1977-01-12"), "F","52"),
                Arguments.of(LocalDate.parse("1977-12-01"), "M","01"),
                Arguments.of(LocalDate.parse("1977-12-14"), "M","14")
            )
        @JvmStatic
        fun encodeCityArguments(): List<Arguments> =
            listOf(
                Arguments.of("Breno", "B149")
            )
        @JvmStatic
        fun encodeCfArguments(): List<Arguments> =
            listOf(
                Arguments.of(
                    Person("Silvana","Bonicelli","F","1977-05-01","Breno"),
                    "BNCSVN77E41B149S"),
                Arguments.of(
                        Person("Alessandro","Fiorini","M","1976-09-09","Lovere"),
                "FRNLSN76P09E704H")
            )

        @JvmStatic
        fun wrongPersonArguments(): List<Arguments> =
            listOf(
                Arguments.of(
                    Person("","Bonicelli","F","1977-05-01","Breno"),
                    "Filed Name is required"),
                Arguments.of(
                    Person("Silvana","","F","1977-05-01","Breno"),
                    "Filed Lastname is required"),
                Arguments.of(
                    Person("Silvana","Bonicelli","","1977-05-01","Breno"),
                    "Filed Genre is required"),
                Arguments.of(
                    Person("Silvana","Bonicelli","F","","Breno"),
                    "Filed Date fo Birth is required"),
                Arguments.of(
                    Person("Silvana","Bonicelli","F","1977-05-01",""),
                    "Filed City of Birth is required")
            )
    }
}