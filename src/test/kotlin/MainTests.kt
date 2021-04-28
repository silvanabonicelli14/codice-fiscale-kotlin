import com.cgm.codicefiscale.entities.Person
import com.cgm.codicefiscale.helpers.Genre
import com.cgm.codicefiscale.helpers.getValueFromCommandLine
import com.cgm.codicefiscale.helpers.validatePerson
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.LocalDate

class MainTests {
//    val dataServiceMode = getValueFromCommandLine("data Service mode")

    @ParameterizedTest(name = "encodeLastName function should return Exception with {0}-{1}-{2}-{3}-{4}")
    @MethodSource("wrongPersonArguments")
    fun `validatePerson from input line with empty value throws exception`(
        firstName: String,
        lastName: String,
        dateOfBirth: String,
        genre: String,
        cityOfBirth: String
    ) {
        assertThrows<IllegalArgumentException> { validatePerson(firstName, lastName, dateOfBirth, genre, cityOfBirth) }
    }

    @Test
    fun `validatePerson from input line with not valid Genre  throws exception`() {
        assertThrows<IllegalArgumentException> { validatePerson("Silvana", "bonicelli", "1977-05-01", "blabla", "Breno") }
    }

    @Test
    fun `validatePerson from input line with date major than today throws exception`() {
        assertThrows<IllegalArgumentException> { validatePerson("Silvana", "bonicelli", LocalDate.now().plusDays(1).toString(), "F", "Breno") }
    }

    @Test
    fun `validatePerson from input line with dateof birth today return OK`() {
        val result = validatePerson("Silvana", "bonicelli", LocalDate.now().toString(), "F", "Breno")
        val person = Person("Silvana", "bonicelli", Genre.F, LocalDate.now(),"Breno")
        Assertions.assertTrue(person.firstName == result.firstName)
        Assertions.assertTrue(person.lastName == result.lastName)
        Assertions.assertTrue(person.dateOfBirth == result.dateOfBirth)
        Assertions.assertTrue(person.cityOfBirth == result.cityOfBirth)
        Assertions.assertTrue(person.genre == result.genre)

    }

    @Test
    fun `validatePerson from input line Happy Path`() {
        val result = validatePerson("Silvana", "bonicelli", "1977-05-01", "F", "Breno")
        val person = Person("Silvana", "bonicelli", Genre.F, LocalDate.parse("1977-05-01"),"Breno")
        Assertions.assertTrue(person.firstName == result.firstName)
        Assertions.assertTrue(person.lastName == result.lastName)
        Assertions.assertTrue(person.dateOfBirth == result.dateOfBirth)
        Assertions.assertTrue(person.cityOfBirth == result.cityOfBirth)
        Assertions.assertTrue(person.genre == result.genre)

    }

    companion object {
        @JvmStatic
        fun wrongPersonArguments(): List<Arguments> =
            listOf(
                Arguments.of("","","","",""),
                Arguments.of("","Bonicelli","1977-05-01","F","Breno"),
                Arguments.of("Silvana","","1977-05-01","F","Breno"),
                Arguments.of("Silvana","Bonicelli","","F","Breno"),
                Arguments.of("Silvana","Bonicelli","1977-05-01","","Breno"),
                Arguments.of("Silvana","Bonicelli","1977-05-01","F","")
            )
    }
}