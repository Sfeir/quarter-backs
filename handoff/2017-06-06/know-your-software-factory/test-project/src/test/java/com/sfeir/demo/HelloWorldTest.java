package com.sfeir.demo;

import org.junit.Test;

import java.io.PrintStream;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by julienfurgerot on 03/05/2017.
 */
public class HelloWorldTest {

    @Test
    public void sayHello() throws Exception {
        // Given
        PrintStream out = mock( PrintStream.class );

        HelloWorld helo = new HelloWorld( out );

        // When
        helo.sayHello( "Sfeirians");

        // Then
        verify( out ).println( anyString() );
    }

    @Test( expected = Exception.class )
    public void sayHelloWithError() throws Exception {
        // Given
        PrintStream out = mock( PrintStream.class );

        HelloWorld helo = new HelloWorld( out );

        // When
        helo.sayHelloWithError( "Sfeirians");

        // Then
        // expect exception
    }
}