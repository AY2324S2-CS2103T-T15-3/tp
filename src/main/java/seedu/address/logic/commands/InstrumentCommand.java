package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.attendance.Attendance;
import seedu.address.model.person.Address;
import seedu.address.model.person.Birthday;
import seedu.address.model.person.Email;
import seedu.address.model.person.Instrument;
import seedu.address.model.person.MatriculationYear;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Represents a command that Assigns an instrument to a person in the address book.
 */
public class InstrumentCommand extends Command {
    public static final String MESSAGE_ARGUMENTS = "Indexes: %1$d, Instrument: %2$s";

    public static final String COMMAND_WORD = "assign";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Assigns an instrument to the persons identified "
            + "by the index numbers used in the last person listing. \n"
            + "Parameters: INDEXES (must be positive integers separated by a whitespace) "
            + "i/[INSTRUMENT] (only alphanumeric characters)\n"
            + "Example: " + COMMAND_WORD + " 1 2 "
            + "i/Flute";
    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Persons: %1$s";

    private final Set<Index> indexes;
    private final Instrument instrument;

    /**
     * Constructs an InstrumentCommand to add the specified instrument to the persons identified by the indexes.
     *
     * @param indexes Set of indexes of the persons to be assigned the instrument.
     * @param instrument Instrument to be assigned to the persons.
     */
    public InstrumentCommand(Set<Index> indexes, Instrument instrument) {
        requireAllNonNull(indexes, instrument);

        this.indexes = indexes;
        this.instrument = instrument;
    }

    /**
     * Executes the InstrumentCommand to assign the instrument to the persons identified by the indexes.
     *
     * @param model The model which the command should operate on.
     * @return A command result with the success message of instrument assigned.
     * @throws CommandException If the index is invalid or the instrument is already assigned to the person.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        Set<Name> editedNames = new HashSet<>();

        for (Index index : this.indexes) {
            requireNonNull(model);
            List<Person> lastShownList = model.getFilteredPersonList();

            if (index.getZeroBased() >= lastShownList.size()) {
                throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            }

            Person personToEdit = lastShownList.get(index.getZeroBased());
            Person editedPerson = createEditedPerson(personToEdit, instrument);
            editedNames.add(editedPerson.getName());

            if (personToEdit.getInstrument().equals(instrument)) {
                throw new CommandException(String.format(Messages.MESSAGE_DUPLICATE_INSTRUMENT,
                        personToEdit.getName()));
            }

            model.setPerson(personToEdit, editedPerson);
            model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        }

        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, editedNames));
    }

    /**
     * Creates and returns a person with the updated instrument.
     *
     * @param personToEdit The person to be edited.
     * @param instrument The instrument to be assigned to the person.
     * @return A person with the updated instrument.
     */
    private static Person createEditedPerson(Person personToEdit, Instrument instrument) {
        assert personToEdit != null;

        Name updatedName = personToEdit.getName();
        Phone updatedPhone = personToEdit.getPhone();
        Email updatedEmail = personToEdit.getEmail();
        Address updatedAddress = personToEdit.getAddress();
        Birthday updatedBirthday = personToEdit.getBirthday();
        MatriculationYear updatedMatriculationYear = personToEdit.getMatriculationYear();
        Instrument updatedInstrument = instrument;
        Set<Tag> updatedTags = personToEdit.getTags();
        Set<Attendance> updatedAttendances = personToEdit.getAttendances();

        return new Person(updatedName, updatedPhone, updatedEmail, updatedAddress,
                updatedBirthday, updatedMatriculationYear, updatedInstrument, updatedTags, updatedAttendances);
    }

    /**
     * Checks if the InstrumentCommand is equal to another object.
     *
     * @param other The other object to compare with.
     * @return True if both InstrumentCommands have the same indexes and instrument.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof InstrumentCommand)) {
            return false;
        }

        InstrumentCommand e = (InstrumentCommand) other;
        return indexes.equals(e.indexes)
                && instrument.equals(e.instrument);
    }
}
