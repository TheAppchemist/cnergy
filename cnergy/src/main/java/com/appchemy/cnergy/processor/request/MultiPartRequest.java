package com.appchemy.cnergy.processor.request;

import com.appchemy.cnergy.processor.request.cnergy.multipart.Multipart;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class MultiPartRequest extends Request
{
    private String boundary = "AaB03x";
    private ByteArrayOutputStream baos;
    private String endline = "\r\n";
    ArrayList<Multipart> multiparts;

    public MultiPartRequest(int id)
    {
        super(POST, id);

        baos = new ByteArrayOutputStream();
        multiparts = new ArrayList<>();

        setHeader("Content-Type", "multipart/form-data;boundary=" + boundary);
        //setHeader("Connection", "Keep-Alive");
        setHeader("ENCTYPE", "multipart/form-data");
        boundary = "--" + boundary;
    }

    public void addPart(Multipart multipart)
    {
        multiparts.add(multipart);
    }

    @Override
    public byte[] getData()
    {
        byte[] bytes = {};
        try {
            for (Multipart multipart : multiparts) {
                baos.write((boundary + endline).getBytes());
                if (multipart.getContentType() == null) {
                    baos.write(("Content-Disposition: form-data; name=\"" + multipart.getName() + "\"" + endline).getBytes());
                }
                else
                {
                    baos.write(("Content-Disposition: form-data; name=\"" + multipart.getName() + "\"; filename=\"" + multipart.getFilename() +
                            "\";" + endline).getBytes());
                    baos.write(("Content-Type: " + multipart.getContentType() + endline).getBytes());
                }

                baos.write((endline).getBytes());
                baos.write(multipart.getData());
                baos.write(endline.getBytes());
            }

            baos.write((boundary + "--").getBytes());
            bytes = baos.toByteArray();
            //Log.i("[Multipart]", new String(baos.toByteArray()));
            baos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setHeader("Content-Length", "" + baos.size());

        return bytes;
    }
}
