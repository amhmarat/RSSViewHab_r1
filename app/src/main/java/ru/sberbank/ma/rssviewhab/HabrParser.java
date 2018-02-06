package ru.sberbank.ma.rssviewhab;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by internet on 05.02.2018.
 */

public class HabrParser {
    public static void xmlParser(Resources resources, String str) {
        try {
            boolean inDataItemTag = false;
            String currentTagName = "";
            Habr habr = null;
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(new StringReader(str));

            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        currentTagName = parser.getName();
                        if (currentTagName.equals("item")) {
                            inDataItemTag = true;
                            habr = new Habr();
                            HabrPool.addHabr(habr);
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        if (parser.getName().equals("item"))
                            inDataItemTag = false;
                        currentTagName = "";
                        break;
                    case XmlPullParser.TEXT:
                        if (inDataItemTag) {
                            switch (currentTagName) {
                                case "title":
                                    habr.setTitle(parser.getText());
                                    break;
                                case "link":
                                    habr.setLinkToFullPost(parser.getText());
                                case "description":
                                    String rawDescription = parser.getText();

                                    String finalDescription = rawDescription;
                                    habr.setDescription(finalDescription);

                                    Pattern pattern = Pattern.compile("src=\"(.*?)\"");
                                    Matcher matcher = pattern.matcher(rawDescription);
                                    if (matcher.find())
                                        habr.setImageUrl(matcher.group(1));
                                    if (habr.getImageUrl() == null) {
                                        Bitmap bitmap = BitmapFactory.decodeResource(resources, R.drawable.habra);
                                        habr.setImage(bitmap);
                                    }
                                    break;
                                case "pubDate":
                                    habr.setPubDate(parser.getText());
                                    break;
                                case "category":
                                    habr.setCategory(parser.getText());
                                    break;
                                case "dc:creator":
                                    habr.setCreater(parser.getText());
                                    break;
                                default:
                                    break;
                            }
                        }
                        break;
                }
                eventType = parser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
