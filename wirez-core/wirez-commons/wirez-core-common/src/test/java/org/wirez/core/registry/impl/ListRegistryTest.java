package org.wirez.core.registry.impl;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class ListRegistryTest {

    @Mock
    private List<Object> list;

    private ListRegistry<Object> tested;

    private final KeyProvider<Object> keyProvider = Object::toString;

    @Before
    public void setup() throws Exception {
        tested = new ListRegistry<Object>( keyProvider, list );
    }

    @Test
    public void testRegister() {
        final String s = "an string";
        tested.register( s );
        verify( list, times( 1 ) ).add( s );
    }

    @Test
    public void testAdd() {
        final String s = "an string";
        tested.add( 1, s );
        verify( list, times( 1 ) ).add( 1, s );
    }

    @Test
    public void testRemove() {
        final String s = "an string";
        tested.remove( s );
        verify( list, times( 1 ) ).remove( s );
    }

    @Test
    public void testContains() {
        final String s = "an string";
        tested.contains( s );
        verify( list, times( 1 ) ).contains( s );
    }

    @Test
    public void testClear() {
        tested.clear();
        verify( list, times( 1 ) ).clear();
    }

    @Test
    public void testIndexOf() {
        final String s = "an string";
        tested.indexOf( s );
        verify( list, times( 1 ) ).indexOf( s );
    }

    @Test
    public void testGetItems() {
        final String s1 = "an string 1";
        final String s2 = "an string 2";
        tested = new ListRegistry<Object>( keyProvider, new ArrayList<Object>( 2 ) {{
            add( s1 );
            add( s2 );
        }} );

        Collection<Object> items = tested.getItems();
        assertNotNull( items );
        assertEquals( 2, items.size() );
        Iterator<Object> it = items.iterator();
        assertEquals( s1, it.next() );
        assertEquals( s2, it.next() );
    }

    @Test
    public void testGetItemByKey() {
        final String s1 = "an string 1";
        final String s2 = "an string 2";
        tested = new ListRegistry<Object>( keyProvider, new ArrayList<Object>( 2 ) {{
            add( s1 );
            add( s2 );
        }} );

        Object o1 = tested.getItemByKey( s1 );
        Object o2 = tested.getItemByKey( s2 );

        assertEquals( s1, o1 );
        assertEquals( s2, o2 );
    }

}