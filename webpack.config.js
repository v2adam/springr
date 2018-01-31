var path = require('path');
const webpack = require('webpack');

module.exports = {
    entry: ['babel-polyfill', './src/main/js/src/index.js'],
    cache: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/bundle.js'
    },
    module: {
        loaders: [
            {
                //test: path.join(__dirname, '.'),
                test: /\.jsx?$/,
                exclude: /(node_modules)/,
                loader: 'babel-loader',
                query: {
                    cacheDirectory: true,
                    presets: ['es2015', 'react',  "stage-0", "stage-3"],
                    plugins: ['transform-class-properties']
                }
            },
            {
                test: /\.css$/,
                use: ['style-loader', 'css-loader']
            },
            {
                test: /\.(woff2?|ttf|eot|svg|png|jpe?g|gif)$/,
                loader: 'file-loader'
            },
            {
                test: /\.html$/,
                loader: 'html-loader'
            }
        ]
    },

    resolve: {
        modules: ['src/main/js', 'node_modules'],
        extensions: ['.js', '.jsx'],
    },


    // prod-ra telepítése esetén: webpack -p
    // tartlamazza ezeket: UglifyJsPlugin & LoaderOptionsPlugin + --define process.env.NODE_ENV="production"


};