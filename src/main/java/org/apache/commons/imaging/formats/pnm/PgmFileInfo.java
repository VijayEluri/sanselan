/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.imaging.formats.pnm;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.imaging.ImageFormat;
import org.apache.commons.imaging.ImageInfo;

public class PgmFileInfo extends FileInfo
{
    private final int max; // TODO: handle max

    public PgmFileInfo(int width, int height, boolean RAWBITS, int max)
    {
        super(width, height, RAWBITS);

        this.max = max;
    }

    @Override
    public int getNumComponents()
    {
        return 1;
    }

    @Override
    public int getBitDepth()
    {
        return 8;
    }

    @Override
    public ImageFormat getImageType()
    {
        return ImageFormat.IMAGE_FORMAT_PPM;
    }

    @Override
    public String getImageTypeDescription()
    {
        return "PGM: portable pixmap file    format";
    }

    @Override
    public String getMIMEType()
    {
        return "image/x-portable-pixmap";
    }

    @Override
    public int getColorType()
    {
        return ImageInfo.COLOR_TYPE_RGB;
    }

    @Override
    public int getRGB(InputStream is) throws IOException
    {
        int sample = is.read();
        if (sample < 0)
            throw new IOException("PGM: Unexpected EOF");

        int alpha = 0xff;

        int rgb = ((0xff & alpha) << 24) | ((0xff & sample) << 16)
                | ((0xff & sample) << 8) | ((0xff & sample) << 0);

        return rgb;
    }

    @Override
    public int getRGB(WhiteSpaceReader wsr) throws IOException
    {
        int sample = Integer.parseInt(wsr.readtoWhiteSpace());

        int alpha = 0xff;

        int rgb = ((0xff & alpha) << 24) | ((0xff & sample) << 16)
                | ((0xff & sample) << 8) | ((0xff & sample) << 0);

        return rgb;
    }

}