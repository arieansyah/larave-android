<?php

use Illuminate\Http\Request;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

//Auth::routes();
Route::post('register', 'RegisterController@register');
Route::post('login', 'LoginController@login');
Route::get('getUser/{username}', 'LoginController@getUser');
//Lapangan
Route::get('lapangan/data', 'LapanganController@listData')->name('lapangan.data');
Route::resource('lapangan', 'LapanganController');

Route::get('tes', 'JadwalController@tes');
Route::get('jadwal/mobile', 'JadwalController@listData_Mobile');
Route::get('jadwal/data/', 'JadwalController@listData')->name('jadwal.data');
Route::get('jadwal/{id}/edit', 'JadwalController@edit');
Route::get('jadwal/{id}/editAfter', 'JadwalController@editAfter');
Route::post('jadwal/refresh', 'JadwalController@refresh')->name('jadwal.refresh');
//Route::post('jadwal', 'JadwalController@store')->name('jadwal.store');
Route::delete('jadwal/{id}', 'JadwalController@destroy');
Route::get('jadwal', 'JadwalController@index');
//Route::patch('jadwal/{id}', 'JadwalController@update');
//Route::controller('/jadwal', 'JadwalController');
Route::resource('jadwal', 'JadwalController');

Route::middleware('auth:api')->get('/user', function (Request $request) {
    dd("salut");
});
