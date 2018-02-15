package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {

    private AddressBook addressBook;

    private List<ReadOnlyPerson> listUnsorted;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), new UniqueTagList());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), new UniqueTagList());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), new UniqueTagList());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                new UniqueTagList());

        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);

        listUnsorted = TestUtil.createList(johnDoe, janeDoe, davidGrant, samDoe);
    }



    @Test
    /**
     * Creates a new sort command.
     *
     */
    public SortCommand createSortCommand() {

        SortCommand command = new SortCommand();
        command.setData(addressBook, listUnsorted);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(SortCommand sortCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = sortCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook, actualAddressBook);
    }

    @Test
    public void execute(){
        assertSortingSuccessful(addressBook);
    }

    /**
     * Asserts that the address book can be successfully sorted.
     *
     * The addressBook passed in will not be modified (no side effects).
     *
     */
    private void assertSortingSuccessful(AddressBook addressBook){


        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        expectedAddressBook.sort();
        String expectedMessage = SortCommand.MESSAGE_SUCCESS;;

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        SortCommand command = createSortCommand();
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);
    }
}