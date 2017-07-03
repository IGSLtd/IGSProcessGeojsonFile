package com.igs.geodata.process;

import com.google.gson.Gson;
import static org.junit.Assert.*;

import java.io.File;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Test;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.igs.geodata.process.dao.RockCategoryDao;
import com.igs.geodata.process.entity.Coordinate;
import com.igs.geodata.process.entity.RockCategory;
import java.util.ArrayList;
import org.hibernate.type.Type;

/**
 *
 * @author zahid
 */
public class IGSGeojsonExtractorTest extends IGSTest {

    private final JsonParser parser = new JsonParser();
    private final String testColumnValue = "TEST-PALEOGENE-TEST";
    private final String fullJson = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UIG\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE\",\"LEX_RCS\":\"UIG-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":null,\"BED_EQ\":null,\"BED_EQ_D\":null,\"MB_EQ\":null,\"MB_EQ_D\":null,\"FM_EQ\":null,\"FM_EQ_D\":null,\"SUBGP_EQ\":null,\"SUBGP_EQ_D\":null,\"GP_EQ\":null,\"GP_EQ_D\":null,\"SUPGP_EQ\":null,\"SUPGP_EQ_D\":null,\"MAX_TIME_D\":\"PALAEOGENE\",\"MIN_TIME_D\":\"PALAEOGENE\",\"MAX_TIME_Y\":65000000,\"MIN_TIME_Y\":23800000,\"MAX_INDEX\":1139999,\"MIN_INDEX\":1139999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"PALAEOGENE\",\"MIN_PERIOD\":\"PALAEOGENE\",\"MAX_ERA\":\"CAINOZOIC\",\"MIN_ERA\":\"CAINOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1139999_UIG-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"G\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":795,\"BGSREF_LEX\":9,\"BGSREF_FM\":9,\"BGSREF_GP\":9,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18147},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[157309.953,681622.063],[157271.516,681637.125],[157276.406,681663.188],[157341.907,681759.813],[157349.469,681755.215],[157361.236,681746.371],[157419.592,681697.856],[157393.906,681660.438],[157368.969,681637.75],[157309.953,681622.063]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UIG\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE\",\"LEX_RCS\":\"UIG-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":null,\"BED_EQ\":null,\"BED_EQ_D\":null,\"MB_EQ\":null,\"MB_EQ_D\":null,\"FM_EQ\":null,\"FM_EQ_D\":null,\"SUBGP_EQ\":null,\"SUBGP_EQ_D\":null,\"GP_EQ\":null,\"GP_EQ_D\":null,\"SUPGP_EQ\":null,\"SUPGP_EQ_D\":null,\"MAX_TIME_D\":\"PALAEOGENE\",\"MIN_TIME_D\":\"PALAEOGENE\",\"MAX_TIME_Y\":65000000,\"MIN_TIME_Y\":23800000,\"MAX_INDEX\":1139999,\"MIN_INDEX\":1139999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"PALAEOGENE\",\"MIN_PERIOD\":\"PALAEOGENE\",\"MAX_ERA\":\"CAINOZOIC\",\"MIN_ERA\":\"CAINOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1139999_UIG-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"G\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":795,\"BGSREF_LEX\":9,\"BGSREF_FM\":9,\"BGSREF_GP\":9,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18147},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[431623.684,582350.129],[431792.828,582322.994],[431800.848,582311.944],[431781.934,582194.046],[431626.594,582347.875],[431623.684,582350.129]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UIG\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE\",\"LEX_RCS\":\"UIG-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":null,\"BED_EQ\":null,\"BED_EQ_D\":null,\"MB_EQ\":null,\"MB_EQ_D\":null,\"FM_EQ\":null,\"FM_EQ_D\":null,\"SUBGP_EQ\":null,\"SUBGP_EQ_D\":null,\"GP_EQ\":null,\"GP_EQ_D\":null,\"SUPGP_EQ\":null,\"SUPGP_EQ_D\":null,\"MAX_TIME_D\":\"PALAEOGENE\",\"MIN_TIME_D\":\"PALAEOGENE\",\"MAX_TIME_Y\":65000000,\"MIN_TIME_Y\":23800000,\"MAX_INDEX\":1139999,\"MIN_INDEX\":1139999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"PALAEOGENE\",\"MIN_PERIOD\":\"PALAEOGENE\",\"MAX_ERA\":\"CAINOZOIC\",\"MIN_ERA\":\"CAINOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1139999_UIG-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"G\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":795,\"BGSREF_LEX\":9,\"BGSREF_FM\":9,\"BGSREF_GP\":9,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18147},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[172145.672,687095.625],[172142.672,687130.875],[172150.703,687163.688],[172245.584,687313.108],[172311.188,687309.984],[172204.781,687117.625],[172145.672,687095.625]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UISD\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN\",\"LEX_RCS\":\"UISD-FELSR\",\"RCS\":\"FELSR\",\"RCS_X\":\"FELSR\",\"RCS_D\":\"FELSIC-ROCK\",\"RANK\":\"INTRUSION\",\"BED_EQ\":\"UISD\",\"BED_EQ_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN\",\"MB_EQ\":\"NoPar\",\"MB_EQ_D\":\"No Parent\",\"FM_EQ\":\"NoPar\",\"FM_EQ_D\":\"No Parent\",\"SUBGP_EQ\":\"NoPar\",\"SUBGP_EQ_D\":\"No Parent\",\"GP_EQ\":\"NoPar\",\"GP_EQ_D\":\"No Parent\",\"SUPGP_EQ\":\"NoPar\",\"SUPGP_EQ_D\":\"No Parent\",\"MAX_TIME_D\":\"LUDLOW\",\"MIN_TIME_D\":\"EARLY DEVONIAN\",\"MAX_TIME_Y\":423000000,\"MIN_TIME_Y\":391000000,\"MAX_INDEX\":1340299,\"MIN_INDEX\":1330399,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":\"LUDLOW\",\"MIN_EPOCH\":\"EARLY DEVONIAN\",\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"SILURIAN\",\"MIN_PERIOD\":\"DEVONIAN\",\"MAX_ERA\":\"PALAEOZOIC\",\"MIN_ERA\":\"PALAEOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1340299_UISD-FELSR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN - FELSIC-ROCK\",\"MAP_CODE\":\"SD\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":909,\"BGSREF_LEX\":773,\"BGSREF_FM\":773,\"BGSREF_GP\":773,\"BGSREF_RK\":909,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18165},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[191211.481,762106.879],[191435.178,761942.186],[191288.451,761902.161],[191275.701,761899.348],[191211.481,762106.879]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UIAZ\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, NEOPROTEROZOIC\",\"LEX_RCS\":\"UIAZ-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":\"INTRUSION\",\"BED_EQ\":\"UIAZ\",\"BED_EQ_D\":\"UNNAMED IGNEOUS INTRUSION, NEOPROTEROZOIC\",\"MB_EQ\":\"NoPar\",\"MB_EQ_D\":\"No Parent\",\"FM_EQ\":\"NoPar\",\"FM_EQ_D\":\"No Parent\",\"SUBGP_EQ\":\"NoPar\",\"SUBGP_EQ_D\":\"No Parent\",\"GP_EQ\":\"NoPar\",\"GP_EQ_D\":\"No Parent\",\"SUPGP_EQ\":\"NoPar\",\"SUPGP_EQ_D\":\"No Parent\",\"MAX_TIME_D\":\"NEOPROTEROZOIC\",\"MIN_TIME_D\":\"NEOPROTEROZOIC\",\"MAX_TIME_Y\":1000000000,\"MIN_TIME_Y\":545000000,\"MAX_INDEX\":2199999,\"MIN_INDEX\":2199999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":null,\"MIN_PERIOD\":null,\"MAX_ERA\":\"NEOPROTEROZOIC\",\"MIN_ERA\":\"NEOPROTEROZOIC\",\"MAX_EON\":\"PROTEROZOIC\",\"MIN_EON\":\"PROTEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"2199999_UIAZ-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, NEOPROTEROZOIC - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"X\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":751,\"BGSREF_LEX\":751,\"BGSREF_FM\":751,\"BGSREF_GP\":751,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18128},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[169093.707,699492.294],[169162.837,699777.513],[169200.241,699605.95],[169252.149,699441.538],[169254.422,699437.753],[169093.707,699492.294]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UIG\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE\",\"LEX_RCS\":\"UIG-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":null,\"BED_EQ\":null,\"BED_EQ_D\":null,\"MB_EQ\":null,\"MB_EQ_D\":null,\"FM_EQ\":null,\"FM_EQ_D\":null,\"SUBGP_EQ\":null,\"SUBGP_EQ_D\":null,\"GP_EQ\":null,\"GP_EQ_D\":null,\"SUPGP_EQ\":null,\"SUPGP_EQ_D\":null,\"MAX_TIME_D\":\"PALAEOGENE\",\"MIN_TIME_D\":\"PALAEOGENE\",\"MAX_TIME_Y\":65000000,\"MIN_TIME_Y\":23800000,\"MAX_INDEX\":1139999,\"MIN_INDEX\":1139999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"PALAEOGENE\",\"MIN_PERIOD\":\"PALAEOGENE\",\"MAX_ERA\":\"CAINOZOIC\",\"MIN_ERA\":\"CAINOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1139999_UIG-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"G\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":795,\"BGSREF_LEX\":9,\"BGSREF_FM\":9,\"BGSREF_GP\":9,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18147},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[164875.576,768356.252],[164877.164,768308.615],[164877.188,768307.821],[164877.208,768307.027],[164879.068,768225.192],[164846.547,768246.688],[164780.344,768269.25],[164672.469,768279.375],[164640.781,768289.063],[164615.234,768316.063],[164605.484,768348.25],[164627.672,768374.313],[164696.922,768402.688],[164730.766,768402.313],[164784.5,768354.0],[164814.125,768341.875],[164875.576,768356.252]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UISD\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN\",\"LEX_RCS\":\"UISD-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":\"INTRUSION\",\"BED_EQ\":\"UISD\",\"BED_EQ_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN\",\"MB_EQ\":\"NoPar\",\"MB_EQ_D\":\"No Parent\",\"FM_EQ\":\"NoPar\",\"FM_EQ_D\":\"No Parent\",\"SUBGP_EQ\":\"NoPar\",\"SUBGP_EQ_D\":\"No Parent\",\"GP_EQ\":\"NoPar\",\"GP_EQ_D\":\"No Parent\",\"SUPGP_EQ\":\"NoPar\",\"SUPGP_EQ_D\":\"No Parent\",\"MAX_TIME_D\":\"LUDLOW\",\"MIN_TIME_D\":\"EARLY DEVONIAN\",\"MAX_TIME_Y\":423000000,\"MIN_TIME_Y\":391000000,\"MAX_INDEX\":1340299,\"MIN_INDEX\":1330399,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":\"LUDLOW\",\"MIN_EPOCH\":\"EARLY DEVONIAN\",\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"SILURIAN\",\"MIN_PERIOD\":\"DEVONIAN\",\"MAX_ERA\":\"PALAEOZOIC\",\"MIN_ERA\":\"PALAEOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1340299_UISD-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"SD\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":773,\"BGSREF_LEX\":773,\"BGSREF_FM\":773,\"BGSREF_GP\":773,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18166},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[430168.906,1173598.875],[429990.875,1173658.375],[430067.969,1173817.0],[430222.373,1173827.814],[430230.375,1173828.375],[430222.373,1173798.499],[430168.906,1173598.875]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UISD\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN\",\"LEX_RCS\":\"UISD-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":\"INTRUSION\",\"BED_EQ\":\"UISD\",\"BED_EQ_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN\",\"MB_EQ\":\"NoPar\",\"MB_EQ_D\":\"No Parent\",\"FM_EQ\":\"NoPar\",\"FM_EQ_D\":\"No Parent\",\"SUBGP_EQ\":\"NoPar\",\"SUBGP_EQ_D\":\"No Parent\",\"GP_EQ\":\"NoPar\",\"GP_EQ_D\":\"No Parent\",\"SUPGP_EQ\":\"NoPar\",\"SUPGP_EQ_D\":\"No Parent\",\"MAX_TIME_D\":\"LUDLOW\",\"MIN_TIME_D\":\"EARLY DEVONIAN\",\"MAX_TIME_Y\":423000000,\"MIN_TIME_Y\":391000000,\"MAX_INDEX\":1340299,\"MIN_INDEX\":1330399,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":\"LUDLOW\",\"MIN_EPOCH\":\"EARLY DEVONIAN\",\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"SILURIAN\",\"MIN_PERIOD\":\"DEVONIAN\",\"MAX_ERA\":\"PALAEOZOIC\",\"MIN_ERA\":\"PALAEOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1340299_UISD-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"SD\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":773,\"BGSREF_LEX\":773,\"BGSREF_FM\":773,\"BGSREF_GP\":773,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18166},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[227222.741,712133.427],[227379.567,712335.104],[227381.988,712333.628],[227393.744,712324.769],[227507.531,712229.927],[227510.113,712227.726],[227512.644,712225.466],[227604.356,712141.712],[227222.741,712133.427]]]}},{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UIG\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE\",\"LEX_RCS\":\"UIG-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":null,\"BED_EQ\":null,\"BED_EQ_D\":null,\"MB_EQ\":null,\"MB_EQ_D\":null,\"FM_EQ\":null,\"FM_EQ_D\":null,\"SUBGP_EQ\":null,\"SUBGP_EQ_D\":null,\"GP_EQ\":null,\"GP_EQ_D\":null,\"SUPGP_EQ\":null,\"SUPGP_EQ_D\":null,\"MAX_TIME_D\":\"PALAEOGENE\",\"MIN_TIME_D\":\"PALAEOGENE\",\"MAX_TIME_Y\":65000000,\"MIN_TIME_Y\":23800000,\"MAX_INDEX\":1139999,\"MIN_INDEX\":1139999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"PALAEOGENE\",\"MIN_PERIOD\":\"PALAEOGENE\",\"MAX_ERA\":\"CAINOZOIC\",\"MIN_ERA\":\"CAINOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1139999_UIG-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"G\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":795,\"BGSREF_LEX\":9,\"BGSREF_FM\":9,\"BGSREF_GP\":9,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18147},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[171866.838,687080.378],[171904.875,687173.5],[171915.266,687348.75],[171920.762,687375.856],[171944.924,687370.217],[172066.869,687351.921],[172073.968,687350.68],[172081.0,687349.1],[172117.465,687339.982],[172101.016,687293.625],[172083.375,687263.188],[172006.828,687185.75],[171966.859,687086.813],[171947.589,687066.459],[171894.447,687074.432],[171888.507,687075.446],[171882.612,687076.697],[171866.838,687080.378]]]}}]}";
    private final String oneCategory = "{\"type\":\"Feature\",\"properties\":{\"LEX\":\"UIG\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE\",\"LEX_RCS\":\"UIG-MFIR\",\"RCS\":\"TEST\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":null,\"BED_EQ\":null,\"BED_EQ_D\":null,\"MB_EQ\":null,\"MB_EQ_D\":null,\"FM_EQ\":null,\"FM_EQ_D\":null,\"SUBGP_EQ\":null,\"SUBGP_EQ_D\":null,\"GP_EQ\":null,\"GP_EQ_D\":null,\"SUPGP_EQ\":null,\"SUPGP_EQ_D\":null,\"MAX_TIME_D\":\"PALAEOGENE\",\"MIN_TIME_D\":\"PALAEOGENE\",\"MAX_TIME_Y\":65000000,\"MIN_TIME_Y\":23800000,\"MAX_INDEX\":1139999,\"MIN_INDEX\":1139999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"PALAEOGENE\",\"MIN_PERIOD\":\"PALAEOGENE\",\"MAX_ERA\":\"CAINOZOIC\",\"MIN_ERA\":\"CAINOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1139999_UIG-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"G\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":795,\"BGSREF_LEX\":9,\"BGSREF_FM\":9,\"BGSREF_GP\":9,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18147},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[157309.953,681622.063],[157271.516,681637.125],[157276.406,681663.188],[157341.907,681759.813],[157349.469,681755.215],[157361.236,681746.371],[157419.592,681697.856],[157393.906,681660.438],[157368.969,681637.75],[157309.953,681622.063]]]}}";

    @Test
    public void testParseFeatures() throws Exception {

        dirty();

        IGSGeojsonExtractor instance = new IGSGeojsonExtractor();
        assertNotNull(instance);
        assertFalse(instance.parseFeatures(null));
        assertFalse(instance.parseFeatures(new JsonArray()));

        JsonObject json = (JsonObject) parser.parse(fullJson);
        assertNotNull(json);
        assertTrue(json.size() > 0);
        assertTrue(json.has("features"));
        JsonArray feature = json.getAsJsonArray("features");
        assertNotNull(feature);
        assertTrue(feature.size() > 0);
        assertEquals(9, feature.size());
        assertFalse(instance.parseFeatures(null));
        assertFalse(instance.parseFeatures(new JsonArray()));
        assertTrue(instance.parseFeatures(feature));
        List<RockCategory> list = getRockCategoryLists();
        assertTrue(list.size() > 0);
        assertEquals(9, list.size());

        assertEquals("UNNAMED IGNEOUS INTRUSION, PALAEOGENE", list.get(0).getCategoryName());
        assertEquals("MFIR", list.get(0).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(0).getRcs_d());
        assertEquals(testColumnValue, list.get(0).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, PALAEOGENE", list.get(1).getCategoryName());
        assertEquals("MFIR", list.get(1).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(1).getRcs_d());
        assertEquals(testColumnValue, list.get(1).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, PALAEOGENE", list.get(2).getCategoryName());
        assertEquals("MFIR", list.get(2).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(2).getRcs_d());
        assertEquals(testColumnValue, list.get(2).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN", list.get(3).getCategoryName());
        assertEquals("FELSR", list.get(3).getRcs());
        assertEquals("FELSIC-ROCK", list.get(3).getRcs_d());
        assertEquals(testColumnValue, list.get(3).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, NEOPROTEROZOIC", list.get(4).getCategoryName());
        assertEquals("MFIR", list.get(4).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(4).getRcs_d());
        assertEquals(testColumnValue, list.get(4).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, PALAEOGENE", list.get(5).getCategoryName());
        assertEquals("MFIR", list.get(5).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(5).getRcs_d());
        assertEquals(testColumnValue, list.get(5).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN", list.get(6).getCategoryName());
        assertEquals("MFIR", list.get(6).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(6).getRcs_d());
        assertEquals(testColumnValue, list.get(6).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, LATE SILURIAN TO EARLY DEVONIAN", list.get(7).getCategoryName());
        assertEquals("MFIR", list.get(7).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(7).getRcs_d());
        assertEquals(testColumnValue, list.get(7).getAge_onegl());

        assertEquals("UNNAMED IGNEOUS INTRUSION, PALAEOGENE", list.get(8).getCategoryName());
        assertEquals("MFIR", list.get(8).getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", list.get(8).getRcs_d());
        assertEquals(testColumnValue, list.get(8).getAge_onegl());

        dirty();
    }

    private void dirty() {

        List<RockCategory> list = getRockCategoryLists();

        if (list.size() > 0) {

            for (int i = 0; i < list.size(); i++) {
                assertTrue(dirty("Coordinate", "categoryId", list.get(i).getCategoryId()));
            }

            assertTrue(dirty("RockCategory", "age_onegl", testColumnValue));
        }
    }

    @Test
    public void testAddRockCategory() throws Exception {

        IGSGeojsonExtractor instance = new IGSGeojsonExtractor();
        assertNotNull(instance);
        dirty();

        String invalidJsonString = "{\"type\":\"Feature\",\"invalid\":{\"LEX\":\"UIG\",\"LEX_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE\",\"LEX_RCS\":\"UIG-MFIR\",\"RCS\":\"MFIR\",\"RCS_X\":\"MFIR\",\"RCS_D\":\"MAFIC IGNEOUS-ROCK\",\"RANK\":null,\"BED_EQ\":null,\"BED_EQ_D\":null,\"MB_EQ\":null,\"MB_EQ_D\":null,\"FM_EQ\":null,\"FM_EQ_D\":null,\"SUBGP_EQ\":null,\"SUBGP_EQ_D\":null,\"GP_EQ\":null,\"GP_EQ_D\":null,\"SUPGP_EQ\":null,\"SUPGP_EQ_D\":null,\"MAX_TIME_D\":\"PALAEOGENE\",\"MIN_TIME_D\":\"PALAEOGENE\",\"MAX_TIME_Y\":65000000,\"MIN_TIME_Y\":23800000,\"MAX_INDEX\":1139999,\"MIN_INDEX\":1139999,\"MAX_AGE\":null,\"MIN_AGE\":null,\"MAX_EPOCH\":null,\"MIN_EPOCH\":null,\"MAX_SUBPER\":null,\"MIN_SUBPER\":null,\"MAX_PERIOD\":\"PALAEOGENE\",\"MIN_PERIOD\":\"PALAEOGENE\",\"MAX_ERA\":\"CAINOZOIC\",\"MIN_ERA\":\"CAINOZOIC\",\"MAX_EON\":\"PHANEROZOIC\",\"MIN_EON\":\"PHANEROZOIC\",\"PREV_NAME\":null,\"BGSTYPE\":\"625k_BEDROCK\",\"LEX_RCS_I\":\"1139999_UIG-MFIR\",\"LEX_RCS_D\":\"UNNAMED IGNEOUS INTRUSION, PALAEOGENE - MAFIC IGNEOUS-ROCK\",\"MAP_CODE\":\"G\",\"AGE_ONEGL\":\"TEST-PALEOGENE-TEST\",\"BGSREF\":795,\"BGSREF_LEX\":9,\"BGSREF_FM\":9,\"BGSREF_GP\":9,\"BGSREF_RK\":773,\"SHEET\":\"625k_V5_Dykes\",\"VERSION\":\"5.17\",\"RELEASED\":\"11- 2-2008\",\"NOM_SCALE\":\"625000\",\"NOM_OS_YR\":\"2005 HC\",\"NOM_BGS_YR\":\"2007\",\"MSLINK\":18147},\"geometry\":{\"type\":\"Polygon\",\"coordinates\":[[[157309.953,681622.063],[157271.516,681637.125],[157276.406,681663.188],[157341.907,681759.813],[157349.469,681755.215],[157361.236,681746.371],[157419.592,681697.856],[157393.906,681660.438],[157368.969,681637.75],[157309.953,681622.063]]]}}";

        RockCategoryDao dao = new RockCategoryDao();

        logger.debug(oneCategory);

        assertFalse(instance.addRockCategory(new JsonObject(), null));
        assertFalse(instance.addRockCategory(new JsonObject(), new RockCategoryDao()));
        assertFalse(instance.addRockCategory(new JsonObject(), dao));
        assertFalse(instance.addRockCategory((JsonObject) parser.parse(invalidJsonString), dao));
        assertTrue(instance.addRockCategory((JsonObject) parser.parse(oneCategory), dao));
        List<RockCategory> list = getRockCategoryLists();
        assertTrue(list.size() == 1);
        RockCategory rc = list.get(0);
        assertEquals("UNNAMED IGNEOUS INTRUSION, PALAEOGENE", rc.getCategoryName());
        assertEquals("TEST", rc.getRcs());
        assertEquals("MAFIC IGNEOUS-ROCK", rc.getRcs_d());
        assertEquals("TEST-PALEOGENE-TEST", rc.getAge_onegl());
        long catId = rc.getCategoryId();
        assertTrue(catId > 0);

        List<Coordinate> coordinates = getCoordinatesWithCategoryId(catId);
        assertEquals(10, coordinates.size());
        assertEquals(157309.953, coordinates.get(0).getxAxis(), 3f);
        assertEquals(681622.063, coordinates.get(0).getyAxis(), 3f);

        assertEquals(157271.516, coordinates.get(1).getxAxis(), 3f);
        assertEquals(681637.125, coordinates.get(1).getyAxis(), 3f);

        assertEquals(157276.406, coordinates.get(2).getxAxis(), 3f);
        assertEquals(681663.188, coordinates.get(2).getyAxis(), 3f);

        assertEquals(157341.907, coordinates.get(3).getxAxis(), 3f);
        assertEquals(681759.813, coordinates.get(3).getyAxis(), 3f);

        assertEquals(157349.469, coordinates.get(4).getxAxis(), 3f);
        assertEquals(681755.215, coordinates.get(4).getyAxis(), 3f);

        assertEquals(157361.236, coordinates.get(5).getxAxis(), 3f);
        assertEquals(681746.371, coordinates.get(5).getyAxis(), 3f);

        assertEquals(157419.592, coordinates.get(6).getxAxis(), 3f);
        assertEquals(681697.856, coordinates.get(6).getyAxis(), 3f);

        assertEquals(157393.906, coordinates.get(7).getxAxis(), 3f);
        assertEquals(681660.438, coordinates.get(7).getyAxis(), 3f);

        assertEquals(157368.969, coordinates.get(8).getxAxis(), 3f);
        assertEquals(681637.750, coordinates.get(8).getyAxis(), 3f);

        assertEquals(157309.953, coordinates.get(9).getxAxis(), 3f);
        assertEquals(681622.063, coordinates.get(9).getyAxis(), 3f);

        dirty();
    }

    
    @Test
    public void testExtractGeojsonFromFile() throws Exception {

        IGSGeojsonExtractor instance = new IGSGeojsonExtractor();
        assertNotNull(instance);
        dirty();
        
        IGSConfiguration config = IGSConfiguration.configure();
        File geojson = new File(config.getJsonFilePath());
        assertTrue(geojson.exists());
        instance.extractGeojson();
        List<RockCategory> list = getRockCategoryLists();
        logger.debug("Row=" + list.size());
        assertTrue(list.size() > 0);
        assertEquals(9, list.size());

        List<Coordinate> c1 = getCoordinatesWithCategoryId(list.get(0).getCategoryId());
        assertNotNull(c1);
        assertEquals(10, c1.size());
        List<Coordinate> c2 = getCoordinatesWithCategoryId(list.get(1).getCategoryId());
        assertNotNull(c2);
        assertEquals(6, c2.size());
        List<Coordinate> c3 = getCoordinatesWithCategoryId(list.get(2).getCategoryId());
        assertNotNull(c3);
        assertEquals(7, c3.size());
        List<Coordinate> c4 = getCoordinatesWithCategoryId(list.get(3).getCategoryId());
        assertNotNull(c4);
        assertEquals(5, c4.size());
        List<Coordinate> c5 = getCoordinatesWithCategoryId(list.get(4).getCategoryId());
        assertNotNull(c5);
        assertEquals(6, c5.size());
        List<Coordinate> c6 = getCoordinatesWithCategoryId(list.get(5).getCategoryId());
        assertNotNull(c6);
        assertEquals(17, c6.size());
        List<Coordinate> c7 = getCoordinatesWithCategoryId(list.get(6).getCategoryId());
        assertNotNull(c7);
        assertEquals(7, c7.size());
        List<Coordinate> c8 = getCoordinatesWithCategoryId(list.get(7).getCategoryId());
        assertNotNull(c8);
        assertEquals(9, c8.size());
        List<Coordinate> c9 = getCoordinatesWithCategoryId(list.get(8).getCategoryId());
        assertNotNull(c9);
        assertEquals(18, c9.size());

        dirty();

    }



    private List<RockCategory> getRockCategoryLists() {

        Session session = IGSConfiguration.configure().getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<RockCategory> list = session.createQuery("from RockCategory where age_onegl= :age_onegl", RockCategory.class)
                .setParameter("age_onegl", testColumnValue)
                .list();

        tx.commit();
        logger.debug(list);

        return list;

    }

}
