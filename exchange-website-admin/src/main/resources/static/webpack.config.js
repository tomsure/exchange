

    var ExtractTextPlugin=require('extract-text-webpack-plugin')
   //加载插件
 
    
    
module.exports={
	entry:'./test/main.js',
	output:{
		path: __dirname + '/test',
		filename:'build.js'
	},
	module:{
		rules:[
	         {
	         	test:/\.css$/,
	         use:ExtractTextPlugin.extract({   //配置loader
	         	fallback:'style-loader',   
	         	use:'css-loader'
	         })
		      }
		      ]

           },
            plugins:[
	                new ExtractTextPlugin('css/style1.css') ,  
                    new ExtractTextPlugin('css1/style2.css')   
    
             //    进行多次实例化，就可以生成多个css文件,此时在实例化的方法中设置路径和文件名，就是设置打包后生成的css的文件名和目录，此时，文件目录是在output的path中设置的目录的子目录中
	               ]
	
	 
}
