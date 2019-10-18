var express = require('express');
var mysql = require('mysql');
var bodyParser = require('body-parser');


var con =  mysql.createConnection({
	host:'localhost',
	user:'root',
	password:'',
	database:'testmanga'

});
//create Restfull
var app=express();
var publicDir=(__dirname+'/public/');
app.use(express.static(publicDir));
app.use(bodyParser.urlencoded({extended:true}));

//get all BANNER
app.get("/banner",(req,res,next)=>{
	con.query('SELECT * FROM banner',function(error,result,fields){
		con.on('error',function(err){
			console.log('[My SQL error]',err);
		});
		if (result && result.length) {
			res.end(JSON.stringify(result));
		}
		else
		{
			res.end(JSON.stringify("Response nhi aa rha hai sayad."));
		}
	})
});


//get all comic
app.get("/comic",(req,res,next)=>{
	con.query('SELECT * FROM manga',function(error,result,fields){
		con.on('error',function(err){
			console.log('[My SQL error]',err);
		});
		if (result && result.length) {
			res.end(JSON.stringify(result));
		}
		else
		{
			res.end(JSON.stringify("No comic here"));
		}
	})
});



//get Images  by chapter id
app.get("/chapter/:mangaid",(req,res,next)=>{
	con.query('SELECT * FROM chapter where MangaID=?',[req.params.mangaid],function(error,result,fields){
		con.on('error',function(err){
			console.log('[My SQL error]',err);
		});
		if (result && result.length) {
			res.end(JSON.stringify(result));
		}
		else
		{
			res.end(JSON.stringify("No chapter here"));
		}
	})
});



app.get("/links/:chapterid",(req,res,next)=>{
	con.query('SELECT * FROM link  where ChapterId=?',[req.params.chapterid],function(error,result,fields){
		con.on('error',function(err){
			console.log('[My SQL error]',err);
		});
		if (result && result.length) {
			res.end(JSON.stringify(result));
		}
		else
		{
			res.end(JSON.stringify("No chapter here"));
		}
	})
});


//start server
app.listen(3000,()=>{
	console.log('hemant comic API');
})
