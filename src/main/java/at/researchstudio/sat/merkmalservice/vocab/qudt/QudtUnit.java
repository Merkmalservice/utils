package at.researchstudio.sat.merkmalservice.vocab.qudt;

import java.lang.invoke.MethodHandles;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class QudtUnit {
    private static final Logger logger =
            LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    public static final String METRE = "http://qudt.org/vocab/unit/M";
    public static final String CENTIMETRE = "http://qudt.org/vocab/unit/CentiM";
    public static final String MILLIMETRE = "http://qudt.org/vocab/unit/MilliM";
    public static final String KILOMETRE = "http://qudt.org/vocab/unit/KiloM";
    public static final String DECIMETRE = "http://qudt.org/vocab/unit/DeciM";
    public static final String SQUARE_METRE = "http://qudt.org/vocab/unit/M2";
    public static final String CUBIC_METRE = "http://qudt.org/vocab/unit/M3";
    public static final String QUARTIC_METRE = "http://qudt.org/vocab/unit/M4";
    public static final String GRAM = "http://qudt.org/vocab/unit/GM";
    public static final String KILOGRAM = "http://qudt.org/vocab/unit/KiloGM";
    public static final String SECOND = "http://qudt.org/vocab/unit/SEC";
    public static final String HERTZ = "http://qudt.org/vocab/unit/HZ";
    public static final String DEGREE_CELSIUS = "http://qudt.org/vocab/unit/DEG_C";
    public static final String AMPERE = "http://qudt.org/vocab/unit/A";
    public static final String VOLT = "http://qudt.org/vocab/unit/V";
    public static final String WATT = "http://qudt.org/vocab/unit/W";
    public static final String KILOWATT = "http://qudt.org/vocab/unit/KiloW";
    public static final String MEGAWATT = "http://qudt.org/vocab/unit/MegaW";
    public static final String NEWTON = "http://qudt.org/vocab/unit/N";
    public static final String KILONEWTON = "http://qudt.org/vocab/unit/KiloN";
    public static final String MEGANEWTON = "http://qudt.org/vocab/unit/MegaN";
    public static final String LUX = "http://qudt.org/vocab/unit/LUX";
    public static final String LUMEN = "http://qudt.org/vocab/unit/LM";
    public static final String CANDELA = "http://qudt.org/vocab/unit/CD";
    public static final String PASCAL = "http://qudt.org/vocab/unit/PA";
    public static final String RADIAN = "http://qudt.org/vocab/unit/RAD";
    public static final String STERADIAN = "http://qudt.org/vocab/unit/SR";
    public static final String KELVIN = "http://qudt.org/vocab/unit/K";
    public static final String JOULE = "http://qudt.org/vocab/unit/J";
    public static final String WATTPERSQUARKEMETERPERKELVIN =
            "http://qudt.org/vocab/unit/W-PER-M2-K";
    public static final String NEWTONPERSQUAREMILLIMETER =
            "http://qudt.org/vocab/unit/N-PER-MilliM2";
    public static final String KILOGRAMPERCUBICMETER = "http://qudt.org/vocab/unit/KiloGM-PER-M3";
    public static final String CUBICMETERPERSECOND = "http://qudt.org/vocab/unit/M3-PER-SEC";
    public static final String DEGREES = "http://qudt.org/vocab/unit/DEG";
    public static final String UNITLESS = "http://qudt.org/vocab/unit/UNITLESS";

    private static String toUuid(String forName) {
        return UUID.nameUUIDFromBytes(forName.getBytes()).toString();
    }
}
