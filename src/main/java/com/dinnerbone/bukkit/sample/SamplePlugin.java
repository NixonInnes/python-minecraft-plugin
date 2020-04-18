
package com.dinnerbone.bukkit.sample;

import java.util.HashMap;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


import org.graalvm.polyglot.Context;
import org.graalvm.polyglot.Source;
import org.graalvm.polyglot.Value;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Sample plugin for Bukkit
 *
 * @author Dinnerbone
 */
public class SamplePlugin extends JavaPlugin {
    private final SamplePlayerListener playerListener = new SamplePlayerListener(this);
    private final SampleBlockListener blockListener = new SampleBlockListener();
    private final HashMap<Player, Boolean> debugees = new HashMap<Player, Boolean>();

    // static String readFile(String path, Charset encoding)
    //   throws IOException
    // {
    //   byte[] encoded = Files.readAllBytes(Paths.get(path));
    //   return new String(encoded, encoding);
    // }

    @Override
    public void onDisable() {
        // TODO: Place any custom disable code here

        // NOTE: All registered events are automatically unregistered when a plugin is disabled

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        getLogger().info("Goodbye world!");
    }

//    @Override
//    public void onEnable() {
//        // TODO: Place any custom enable code here including the registration of any events
//
//        // Register our events
//        PluginManager pm = getServer().getPluginManager();
//        pm.registerEvents(playerListener, this);
//        pm.registerEvents(blockListener, this);
//
//        // Register our commands
//        getCommand("pos").setExecutor(new SamplePosCommand());
//        getCommand("debug").setExecutor(new SampleDebugCommand(this));
//
//        // EXAMPLE: Custom code, here we just output some info so we can check all is well
//        PluginDescriptionFile pdfFile = this.getDescription();
//        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );
//
//
//
//        Context polyglot = Context.create();
//
//        String filename = readFile("test.py");
//        object pyCall = polyglot.eval("python", filename);
//
//        pyCall();
//
//
//        //Value array = polyglot.eval("python", "[1,2,42,4]");
//        //int result = array.getArrayElement(2).asInt();
//        //System.out.println(result);
//
//        //getLogger().info("POLYGLOT "+result);
//    }


    @Override
    public void onEnable() {
        // TODO: Place any custom enable code here including the registration of any events

        // Register our events
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(playerListener, this);
        pm.registerEvents(blockListener, this);

        // Register our commands
        getCommand("pos").setExecutor(new SamplePosCommand());
        getCommand("debug").setExecutor(new SampleDebugCommand(this));

        // EXAMPLE: Custom code, here we just output some info so we can check all is well
        PluginDescriptionFile pdfFile = this.getDescription();
        getLogger().info( pdfFile.getName() + " version " + pdfFile.getVersion() + " is enabled!" );


        Context.Builder builder = Context.newBuilder();
        builder.allowAllAccess(true);
        Context context = builder.build();

        String source = "import polyglot\n" +
                "@polyglot.export_value\n" +
                "def foo(externalInput):\n" +
                "    print('Called with: ' + externalInput)\n" +
                "    return 'Got output'\n\n";

        Source script = Source.create("python", source);
        context.eval(script);
        Value main = context.getPolyglotBindings().getMember("foo");
        getLogger().info("PYTHON FUNC: "+ main);

        Value something = main.execute("myInput");
        getLogger().info("PYTHON RETURNED: " + something);
    }

    public boolean isDebugging(final Player player) {
        if (debugees.containsKey(player)) {
            return debugees.get(player);
        } else {
            return false;
        }
    }

    public void setDebugging(final Player player, final boolean value) {
        debugees.put(player, value);
    }
}
