package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.MatriculationYear;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";
    public static final String MESSAGE_INVALID_FIND_STRING =
            " should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String FIND_STRING_VALIDATION_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param oneBasedIndex The string index to be parsed.
     * @return The parsed index in the form of an Index object.
     * @throws ParseException If the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses {@code indexes} into a {@code Set<Index>} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @param indexes The string of indexes to be parsed.
     * @return The parsed indexes in the form of a Set of Index objects.
     * @throws ParseException If the specified indexes are invalid (not non-zero unsigned integer).
     */
    public static Set<Index> parseIndexes(Collection<String> indexes) throws ParseException {
        requireNonNull(indexes);
        final Set<Index> indexSet = new HashSet<>();
        for (String index : indexes) {
            indexSet.add(parseIndex(index));
        }
        return indexSet;
    }

    /**
     * Parses a {@code String name} into a {@code Name}. Leading and trailing whitespaces will be trimmed.
     *
     * @param name The string name to be parsed.
     * @return The parsed name in the form of a Name object.
     * @throws ParseException If the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}. Leading and trailing whitespaces will be trimmed.
     *
     * @param phone The string phone number to be parsed.
     * @return The parsed phone number in the form of a Phone object.
     * @throws ParseException If the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}. Leading and trailing whitespaces will be trimmed.
     *
     * @param address The string address to be parsed.
     * @return The parsed address in the form of an Address object.
     * @throws ParseException If the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}. Leading and trailing whitespaces will be trimmed.
     *
     * @param email The string email to be parsed.
     * @return The parsed email in the form of an Email object.
     * @throws ParseException If the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String birthday} into an {@code Birthday}. Leading and trailing whitespaces will be trimmed.
     *
     * @param birthday The string birthday to be parsed.
     * @return The parsed birthday in the form of a Birthday object.
     * @throws ParseException If the given {@code birthday} is invalid.
     */
    public static Birthday parseBirthday(String birthday) throws ParseException {
        requireNonNull(birthday);
        String trimmedBirthday = birthday.trim();
        if (!Birthday.isValidBirthday(trimmedBirthday)) {
            throw new ParseException(Birthday.MESSAGE_CONSTRAINTS);
        }
        return new Birthday(trimmedBirthday);
    }

    /**
     * Parses a {@code String matriculationYear} into an {@code MatriculationYear}. Leading and trailing whitespaces
     * will be trimmed.
     *
     * @param matriculationYear The string matriculation year to be parsed.
     * @return The parsed matriculation year in the form of a MatriculationYear object.
     * @throws ParseException If the given {@code matriculationYear} is invalid.
     */
    public static MatriculationYear parseMatriculationYear(String matriculationYear) throws ParseException {
        requireNonNull(matriculationYear);
        String trimmedMatriculationYear = matriculationYear.trim();
        if (!MatriculationYear.isValidMatriculationYear(trimmedMatriculationYear)) {
            throw new ParseException(MatriculationYear.MESSAGE_CONSTRAINTS);
        }
        return new MatriculationYear(trimmedMatriculationYear);
    }

    /**
     * Parses a {@code String instrument} into an {@code Instrument}. Leading and trailing whitespaces will be trimmed.
     *
     * @param instrument The string instrument to be parsed.
     * @return The parsed instrument in the form of an Instrument object.
     * @throws ParseException If the given {@code instrument} is invalid.
     */
    public static Instrument parseInstrument(String instrument) throws ParseException {
        requireNonNull(instrument);
        String trimmedInstrument = instrument.trim();
        if (!Instrument.isValidInstrument(trimmedInstrument)) {
            throw new ParseException(Instrument.MESSAGE_CONSTRAINTS);
        }
        return new Instrument(trimmedInstrument);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}. Leading and trailing whitespaces will be trimmed.
     *
     * @param tag The string tag to be parsed.
     * @return The parsed tag in the form of a Tag object.
     * @throws ParseException If the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     *
     * @param tags The collection of tags to be parsed.
     * @return The parsed tags in the form of a Set of Tag objects.
     * @throws ParseException If the given {@code tags} is invalid.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses a {@code String attendance} into an {@code Attendance}. Leading and trailing whitespaces will be trimmed.
     *
     * @param attendance The string attendance to be parsed.
     * @return The parsed attendance in the form of an Attendance object.
     * @throws ParseException If the given {@code attendance} is invalid.
     */
    public static Attendance parseAttendance(String attendance) throws ParseException {
        requireNonNull(attendance);
        String trimmedAttendance = attendance.trim();
        if (!Attendance.isValidAttendanceDate(trimmedAttendance)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        return new Attendance(LocalDate.parse(trimmedAttendance));
    }

    /**
     * Parses {@code Collection<String> attendances} into a {@code Set<Attendance>}.
     *
     * @param attendances The collection of attendances to be parsed.
     * @return The parsed attendances in the form of a Set of Attendance objects.
     * @throws ParseException If the given {@code attendances} is invalid.
     */
    public static Set<Attendance> parseAttendances(Collection<String> attendances) throws ParseException {
        requireNonNull(attendances);
        final Set<Attendance> attendanceSet = new HashSet<>();
        for (String attendance : attendances) {
            attendanceSet.add(parseAttendance(attendance));
        }
        return attendanceSet;
    }

    /**
     * Parses a {@code String findString} into a {@code String[]} and ensures check validation.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @param findString The string findString to be parsed.
     * @param prefix The prefix of the findString.
     * @return The parsed findString in the form of a String[].
     * @throws ParseException if the given {@code string} is invalid.
     */
    public static String[] parseFindString(String findString, Prefix prefix) throws ParseException {
        requireNonNull(findString);
        String trimmedFindString = findString.trim();
        if (!trimmedFindString.matches(FIND_STRING_VALIDATION_REGEX)) {
            throw new ParseException(prefix + MESSAGE_INVALID_FIND_STRING);
        }
        return findString.split("\\s+");
    }
}
