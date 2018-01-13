<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use Illuminate\Support\Facades\Route;
use Laravel\Passport\Client;

class RegisterController extends Controller
{

    public function __construct(){
      $this->client = Client::find(1);
    }

    public function register(Request $request){


      // dd($request->all());
      $user = new User();
      $user->name = $request->name;
      $user->email = $request->email;
      $user->password = bcrypt($request->password);
      $user->save();
      //User::create($request->all());

      // $params = [
      //   'grant_type' => 'password',
    	// 	'client_id' => $this->client->id,
    	// 	'client_secret' => $this->client->secret,
      //   'username' => $request->email,
      //   'password' => $request->password,
    	// 	'scope' => '*'
      // ];
      //
      // $request->request->add($params);
      //
      // $proxy = Request::create('oauth/token', 'POST');
      //
      // return Route::dispatch($proxy);
    }
}
