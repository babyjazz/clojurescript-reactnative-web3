How to use web3.js v.1.x.x in clojurescript using re-natal without clojurescript dependency

### Environment

-   Nodejs v.8+ # Test in node v.10.9.0
-   react-native-cli v.2.0.1
-   react-native v.0.55.4
-   re-natal v.0.9.0
-   Leiningen 2.8.1 on Java 1.8.0_181 Java HotSpot(TM) 64-Bit Server VM

### Installatin

```
yarn
./node_modules/.bin/rn-nodeify --hack --install
```

### How to start

```
rlwrap lein fighweel android
react-native run-android
```

### Full installation and start from start project

```
re-natal init project
cd project
yarn
re-natal use-android-device avd  # Learn more: https://github.com/drapanjanas/re-natal
yarn add -D babel-preset-es2015
yarn add web3
re-natal require web3 android
re-natal use-figwheel
yarn add react-native-crypto react-native-randombytes
react-native link react-native-randombytes

# Do two commands twice if getting error in first times
yarn add -D tradle/rn-nodeify
./node_modules/.bin/rn-nodeify --hack --install

# edit index file (index.android.js or index.ios.js)
# Add following command to top of file
import './shim'   # Do not use require()
import './global'
import 'crypto'

rlwrap lein figwheel android
react-native run-android
```

### Issue

This repo created for fix undefined 'crypto' module when using web3

### License

MIT
