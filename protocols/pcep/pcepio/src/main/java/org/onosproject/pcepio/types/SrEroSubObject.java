/*
 * Copyright 2015 Open Networking Laboratory
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.onosproject.pcepio.types;

import java.util.Objects;

import org.jboss.netty.buffer.ChannelBuffer;
import org.onosproject.pcepio.protocol.PcepNai;
import org.onosproject.pcepio.protocol.PcepVersion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.MoreObjects;

/**
 * Provides SrEroSubObject.
 */
public class SrEroSubObject implements PcepValueType {
    /*
    SR-ERO subobject: (draft-ietf-pce-segment-routing-00)
    0                   1                   2                   3
    0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
    +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
    |L|    Type     |     Length    |  ST   |     Flags     |F|S|C|M|
    +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
    |                              SID                              |
    +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
    //                        NAI (variable)                       //
    +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+



    NAI

          0                   1                   2                   3
      0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                      Local IPv4 address                       |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                     Remote IPv4 address                       |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

                        NAI for IPv4 Adjacency

           0                   1                   2                   3
      0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     //               Local IPv6 address (16 bytes)                 //
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     //               Remote IPv6 address (16 bytes)                //
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

                       NAI for IPv6 adjacency

           0                   1                   2                   3
      0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                      Local Node-ID                            |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                    Local Interface ID                         |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                      Remote Node-ID                           |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
     |                   Remote Interface ID                         |
     +-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

           NAI for Unnumbered adjacency with IPv4 Node IDs

     */
    protected static final Logger log = LoggerFactory.getLogger(SrEroSubObject.class);

    public static final short TYPE = 0x60; //TODO : type to be defined
    public static final short LENGTH = 12;
    public static final short VALUE_LENGTH = 10;
    public static final int SET = 1;
    public static final byte MFLAG_SET = 0x01;
    public static final byte CFLAG_SET = 0x02;
    public static final byte SFLAG_SET = 0x04;
    public static final byte FFLAG_SET = 0x08;
    public static final byte SHIFT_ST = 12;

    private final boolean bFFlag;
    private final boolean bSFlag;
    private final boolean bCFlag;
    private final boolean bMFlag;
    private final byte st;

    private final int sID;
    private final PcepNai nai;

    /**
     * Constructor to initialize member variables.
     *
     * @param st SID type
     * @param bFFlag F flag
     * @param bSFlag S flag
     * @param bCFlag C flag
     * @param bMFlag M flag
     * @param sID segment identifier value
     * @param nai NAI associated with SID
     */
    public SrEroSubObject(byte st, boolean bFFlag, boolean bSFlag, boolean bCFlag, boolean bMFlag, int sID,
            PcepNai nai) {
        this.st = st;
        this.bFFlag = bFFlag;
        this.bSFlag = bSFlag;
        this.bCFlag = bCFlag;
        this.bMFlag = bMFlag;
        this.sID = sID;
        this.nai = nai;
    }

    /**
     * Creates object of SrEroSubObject.
     *
     * @param st SID type
     * @param bFFlag F flag
     * @param bSFlag S flag
     * @param bCFlag C flag
     * @param bMFlag M flag
     * @param sID segment identifier value
     * @param nai NAI associated with SID
     * @return object of SrEroSubObject
     */
    public static SrEroSubObject of(byte st, boolean bFFlag, boolean bSFlag, boolean bCFlag, boolean bMFlag, int sID,
            PcepNai nai) {
        return new SrEroSubObject(st, bFFlag, bSFlag, bCFlag, bMFlag, sID, nai);
    }

    /**
     * Returns SID type.
     * @return st sid type
     */
    public byte getSt() {
        return st;
    }

    /**
     * Returns bFFlag.
     * @return bFFlag
     */
    public boolean getFFlag() {
        return bFFlag;
    }

    /**
     * Returns bSFlag.
     * @return bSFlag
     */
    public boolean getSFlag() {
        return bSFlag;
    }

    /**
     * Returns bCFlag.
     * @return bCFlag
     */
    public boolean getCFlag() {
        return bCFlag;
    }

    /**
     * Returns bMFlag.
     * @return bMFlag
     */
    public boolean getMFlag() {
        return bMFlag;
    }

    /**
     * Returns sID.
     * @return sID
     */
    public int getSID() {
        return sID;
    }

    /**
     * Returns nai.
     * @return nai
     */
    public PcepNai getNai() {
        return nai;
    }

    @Override
    public PcepVersion getVersion() {
        return PcepVersion.PCEP_1;
    }

    @Override
    public short getType() {
        return TYPE;
    }

    @Override
    public short getLength() {
        return LENGTH;
    }

    @Override
    public int hashCode() {
        return Objects.hash(st, bFFlag, bSFlag, bCFlag, bMFlag, sID, nai);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof SrEroSubObject) {
            SrEroSubObject other = (SrEroSubObject) obj;
            return Objects.equals(this.st, other.st) && Objects.equals(this.bFFlag, other.bFFlag)
                    && Objects.equals(this.bSFlag, other.bSFlag) && Objects.equals(this.bCFlag, other.bCFlag)
                    && Objects.equals(this.bMFlag, other.bMFlag) && Objects.equals(this.sID, other.sID)
                    && Objects.equals(this.nai, other.nai);
        }
        return false;
    }

    @Override
    public int write(ChannelBuffer c) {
        int iLenStartIndex = c.writerIndex();

        c.writeShort(TYPE);
        c.writeShort(LENGTH);

        short temp = 0;
        if (bMFlag) {
            temp = (short) (temp | MFLAG_SET);
        }
        if (bCFlag) {
            temp = (short) (temp | CFLAG_SET);
        }
        if (bSFlag) {
            temp = (short) (temp | SFLAG_SET);
        }
        if (bFFlag) {
            temp = (short) (temp | FFLAG_SET);
        }
        short tempST = (short) (st << SHIFT_ST);
        temp = (short) (temp | tempST);
        c.writeShort(temp);
        c.writeInt(sID);
        nai.write(c);

        return c.writerIndex() - iLenStartIndex;
    }

    /**
     * Reads the channel buffer and returns object of SrEroSubObject.
     * @param c of type channel buffer
     * @return object of SrEroSubObject
     */
    public static PcepValueType read(ChannelBuffer c) {
        short temp = c.readShort();
        boolean bMFlag;
        boolean bCFlag;
        boolean bSFlag;
        boolean bFFlag;
        byte st;
        PcepNai nai = null;

        bMFlag = (temp & MFLAG_SET) == MFLAG_SET;
        bCFlag = (temp & CFLAG_SET) == CFLAG_SET;
        bSFlag = (temp & SFLAG_SET) == SFLAG_SET;
        bFFlag = (temp & FFLAG_SET) == FFLAG_SET;

        st = (byte) (temp >> SHIFT_ST);

        int sID = c.readInt();
        switch (st) {
        case 0x01:
            nai = PcepNaiIpv4NodeId.read(c);
            break;
        case 0x02:
            nai = PcepNaiIpv6NodeId.read(c);
            break;
        case 0x03:
            nai = PcepNaiIpv4Adjacency.read(c);
            break;
        case 0x04:
            nai = PcepNaiIpv6Adjacency.read(c);
            break;
        case 0x05:
            nai = PcepNaiUnnumberedAdjacencyIpv4.read(c);
            break;
        default:
            nai = null;
            break;
        }

        return new SrEroSubObject(st, bFFlag, bSFlag, bCFlag, bMFlag, sID, nai);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(getClass())
                .add("Type", TYPE)
                .add("Length", LENGTH)
                .add("st", st)
                .add("bFflag", bFFlag)
                .add("bSFlag", bSFlag)
                .add("bCFlag", bCFlag)
                .add("bMFlag", bMFlag)
                .add("sID", sID)
                .add("nAI", nai)
                .toString();
    }
}
