package org.wirez.core.registry.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.wirez.core.command.Command;
import org.wirez.core.registry.exception.RegistrySizeExceededException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Stack;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CommandRegistryImplTest {

    private CommandRegistryImpl<Command> tested;

    @Mock
    private Command command;

    @Mock
    private Command command1;

    @Before
    public void setup() throws Exception {

        tested = new CommandRegistryImpl<Command>();

    }

    @Test
    public void testRegisterCommand() {

        tested.register( command );

        Iterable<Command> result = tested.peek();

        assertNotNull( result );

        assertEquals( command, result.iterator().next() );

    }

    @Test
    public void testRegisterCommands() {

        Collection<Command> commands = new ArrayList<Command>( 2 ) {{
            add( command );
            add( command1 );
        }};

        tested.register( commands );

        Iterable<Command> result = tested.peek();

        assertNotNull( result );

        Iterator<Command> it = result.iterator();
        assertEquals( command1, it.next() );
        assertEquals( command, it.next() );

    }

    @Test
    public void testClear() {

        Collection<Command> commands = new ArrayList<Command>( 2 ) {{
            add( command );
            add( command1 );
        }};

        tested.register( commands );
        tested.clear();;

        Iterable<Iterable<Command>> result = tested.getCommandHistory();

        assertNotNull( result );

        assertFalse( result.iterator().hasNext() );

    }

    @Test
    public void testPeek() {

        tested.register( command );

        Iterable<Command> result = tested.peek();

        assertNotNull( result );

        assertEquals( command, result.iterator().next() );

        Iterable<Iterable<Command>> result2 = tested.getCommandHistory();

        assertNotNull( result2 );

        assertTrue( result.iterator().hasNext() );

    }

    @Test
    public void testPop() {

        tested.register( command );

        Iterable<Command> result = tested.pop();

        assertNotNull( result );

        assertEquals( command, result.iterator().next() );

        Iterable<Iterable<Command>> result2 = tested.getCommandHistory();

        assertNotNull( result2 );

        assertFalse( result2.iterator().hasNext() );

    }

    @Test
    public void testGetCommandSize() {

        tested.register( command );

        Collection<Command> commands = new ArrayList<Command>( 2 ) {{
            add( command );
            add( command1 );
        }};

        tested.register( commands );

        int size = tested.getCommandHistorySize();

        assertEquals( 2, size );

    }

    @Test
    public void testGetCommandHistory() {

        tested.register( command );

        Collection<Command> commands = new ArrayList<Command>( 2 ) {{
            add( command );
            add( command1 );
        }};

        tested.register( commands );

        Iterable<Iterable<Command>> result = tested.getCommandHistory();

        assertNotNull( result );

        Iterator<Iterable<Command>> it = result.iterator();

        Iterable<Command> r1 = it.next();

        assertNotNull( r1 );

        Iterator<Command> it1 = r1.iterator();

        Command cr1 = it1.next();
        assertEquals( command1, cr1 );

        Command cr11 = it1.next();
        assertEquals( command, cr11 );

        Iterable<Command> r2 = it.next();
        Iterator<Command> it2 = r2.iterator();

        Command cr2 = it2.next();
        assertEquals( command, cr2 );
    }

    @Test( expected = RegistrySizeExceededException.class )
    public void testStackSize() {

        tested.setMaxSize( 1 );

        tested.register( command );

        tested.register( command );

    }

}
