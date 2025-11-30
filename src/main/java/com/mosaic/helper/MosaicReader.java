package com.mosaic.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class MosaicReader
{
    public record Mosaic(int m, int n, int l, int h, int[][] grid)
    {
    };

    public static Mosaic readFile()
    {
        String fileName = "mosaic/mosaic.in";
        InputStream is = getFileFromResourceAsStream(fileName);
        try (InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
                BufferedReader reader = new BufferedReader(streamReader))
        {
            String line = reader.readLine();
            String[] nums = line.split(" ");
            int r = 0;
            int m = Integer.parseInt(nums[0]);
            int n = Integer.parseInt(nums[1]);
            int l = Integer.parseInt(nums[2]);
            int h = Integer.parseInt(nums[3]);
            int[][] grid = new int[m][n];
            while ((line = reader.readLine()) != null)
            {
                var chars = line.toCharArray();
                for (int i = 0; i < n; ++i)
                {
                    grid[r][i] = chars[i] == 'W' ? 1 : 0;
                }
                ++r;
            }
            return new Mosaic(m, n, l, h, grid);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

    private static InputStream getFileFromResourceAsStream(String fileName)
    {
        // The class loader that loaded the class
        ClassLoader classLoader = MosaicReader.class.getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(fileName);

        // the stream holding the file content
        if (inputStream == null)
        {
            throw new IllegalArgumentException("file not found! " + fileName);
        }
        else
        {
            return inputStream;
        }
    }
}
