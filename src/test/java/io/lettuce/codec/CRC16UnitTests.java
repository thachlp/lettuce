package io.lettuce.codec;

import static io.lettuce.TestTags.UNIT_TEST;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import io.lettuce.core.codec.CRC16;

/**
 * @author Mark Paluch
 */
@Tag(UNIT_TEST)
class CRC16UnitTests {

    static List<Fixture> parameters() {

        List<Fixture> parameters = new ArrayList<>();

        parameters.add(new Fixture("".getBytes(), 0x0));
        parameters.add(new Fixture("123456789".getBytes(), 0x31C3));
        parameters.add(new Fixture("sfger132515".getBytes(), 0xA45C));
        parameters.add(new Fixture("hae9Napahngaikeethievubaibogiech".getBytes(), 0x58CE));
        parameters.add(new Fixture("AAAAAAAAAAAAAAAAAAAAAA".getBytes(), 0x92cd));
        parameters.add(new Fixture("Hello, World!".getBytes(), 0x4FD6));

        return parameters;
    }

    @ParameterizedTest
    @MethodSource("parameters")
    void testCRC16(Fixture fixture) {

        int result = CRC16.crc16(fixture.bytes);
        assertThat(result).describedAs("Expects " + Integer.toHexString(fixture.expected)).isEqualTo(fixture.expected);
    }

    static class Fixture {

        final byte[] bytes;

        final int expected;

        Fixture(byte[] bytes, int expected) {
            this.bytes = bytes;
            this.expected = expected;
        }

        @Override
        public String toString() {
            return "Expects 0x" + Integer.toHexString(expected).toUpperCase();
        }

    }

}
