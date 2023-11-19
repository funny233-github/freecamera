__config()-> {
  'stay_loaded' -> true,

  'commands' -> {
    '' -> 'freecamera',
  },
};

//INIT
global_playerdata = read_file('freecameraData','JSON');
global_playerdata = {};

if(
  global_playerdata == null,
  global_playerdata = {};
);

//UTILS
_error(msg) ->(
  print(player(),format(str('r %s',msg)));
  exit()
);

savedata(player) -> (
  name = str(player);
  global_playerdata:name = {
    'gamemode' -> player~'gamemode',
    'dimension' -> player~'dimension',
    'pos' -> player~'pos',
    'yaw' -> player~'yaw',
    'pitch' -> player~'pitch',
  };
  write_file('freecameraData','JSON',global_playerdata);
);

//COMMANDS
freecamera() -> (
  player = player();
  if(
    //if
    player~'gamemode' == 'spectator',
    if(length(global_playerdata) == 0,_error('error:no data?!'));
    run(str('/execute in %s run tp %s %s %s %s %s %s',
      global_playerdata:player:'dimension',
      player,
      global_playerdata:player:'pos':0,
      global_playerdata:player:'pos':1,
      global_playerdata:player:'pos':2,
      global_playerdata:player:'yaw',
      global_playerdata:player:'pitch',
    ));
    run(str('/execute in %s run gamemode %s %s',
      global_playerdata:player:'dimension',
      global_playerdata:player:'gamemode',
      player,
    ));
    print(player,format(str('bc You are change gamemode to %s!',global_playerdata:player:'gamemode'))),
    //else
    savedata(player);
    run(str('/execute in %s run gamemode spectator %s',
      player~'dimension',
      player,
    ));
    print(player,format('bc You are change gamemode to spectator!'));
  );
);
