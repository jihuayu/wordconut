package plugin;

import com.jihuayu.core.api.Plugin;
import com.jihuayu.core.api.event.ReadEvent;
import com.jihuayu.core.api.event.ReadOverEvent;
import com.jihuayu.core.api.event.ReadyEvent;
import org.greenrobot.eventbus.EventBus;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class ReadPlug implements Plugin {
    public String in;
    @Override
    public void getCommandUsage(List<String> info) {
        info.add("<file>:read from file");
    }

    @Override
    public String getCommandName() {
        return "r";
    }

    @Override
    public void loadCommand(String[]args) {
        if(args.length>0){
            String str = args[0];
            FileReader fr= null;
            try {
                fr = new FileReader(str);

            BufferedReader br=new BufferedReader(fr);
            String line="";
            while ((line=br.readLine())!=null) {
                EventBus.getDefault().post(new ReadEvent(line));
            }
            EventBus.getDefault().post(new ReadOverEvent());
            br.close();
            fr.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
