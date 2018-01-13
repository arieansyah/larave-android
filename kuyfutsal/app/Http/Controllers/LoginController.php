<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\User;
use Auth;

class LoginController extends Controller
{
  public function login(Request $request){

    // $this->validate($request, [
    //   'username' => 'required',
    //   'password' => 'required'
    // ]);

    // $params = [
    //   'grant_type' => 'password',
    //   'client_id' => $this->client->id,
    //   'client_secret' => $this->client->secret,
    //   'username' => $request->email,
    //   'password' => $request->password,
    //   'scope' => '*'
    // ];
    //
    // $request->request->add($params);
    //
    // $proxy = Request::create('oauth/token', 'POST');
    //
    // return Route::dispatch($proxy);

    // $user = User::where('email', $request->email)
    // ->where('password', $request->password)
    // ->first();

    
    if (Auth::attempt(['email' => $request->email, 'password' => $request->password])) {
      echo "success";
    }else {
      echo "failed";
    }

  }
}
