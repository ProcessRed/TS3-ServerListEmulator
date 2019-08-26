# TS3-ServerListEmulator

[![N|Release](https://i.imgur.com/tUd0vOp.png)](https://district24.xyz)

[![N|SonsofScripts](https://img.shields.io/github/v/release/ProcessRed/TS3-ServerListEmulator)](https://github.com/ProcessRed/TS3-ServerListEmulator/releases/tag/1.0)

### Installation

Install Java

```sh
$ sudo apt update
$ sudo apt upgrade
$ sudo apt install default-jre
$ java -version
```

Download the Emulator

```sh
$ wget https://github.com/ProcessRed/TS3-ServerListEmulator/releases/download/1.0/start.sh
$ wget https://github.com/ProcessRed/TS3-ServerListEmulator/releases/download/1.0/Weblistemulator.jar
```

Run the Emulator

```sh
$ chmod 777 start.sh
$ ./start.sh run
```

Setting up the port forwarding

```sh
$ sysctl net.ipv4.ip_forward=1
$ iptables -t nat -A POSTROUTING -j MASQUERADE
$ iptables -t nat -A PREROUTING -p udp --dport 300:40000 -j DNAT --to-destination AdresseDesteamspeakservers:9987
```



License
----

MIT


**Free Software, Hell Yeah!**

**Special Thanks to SonsofScripts!**
