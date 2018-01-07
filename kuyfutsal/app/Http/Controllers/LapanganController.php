<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Lapangan;
use Laravel\Passport\Client;
//use Illuminate\Routing\Route;
use Illuminate\Support\Facades\Route;

class LapanganController extends Controller
{

  private $client;

  public function __construct(){
    $this->client = Client::find(1);
  }

    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index()
    {
        return view('lapangan.index');
    }

    public function listData()
    {

      $lapangan = Lapangan::orderBy('id_lapangan', 'desc')->get();
      $no = 0;
      $data = array();
      foreach($lapangan as $list){
        $no ++;
        $row = array();
        $row[] = $no;
        $row[] = $list->nama;
        $row[] = $list->harga;
        $row[] = $list->handphone;
        $row[] = '<div class="btn-group">
                <a onclick="editForm('.$list->id_lapangan.')" class="btn btn-primary btn-sm"><i class="fa fa-pencil"></i></a>
                <a onclick="deleteData('.$list->id_lapangan.')" class="btn btn-danger btn-sm"><i class="fa fa-trash"></i></a></div>';
        $data[] = $row;
      }

      $output = array("data" => $data);
      return response()->json($output);
    }

    /**
     * Show the form for creating a new resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function create()
    {
        //
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $request)
    {
        $lapangan = new Lapangan;
        $lapangan->nama = $request['nama'];
        $lapangan->alamat = $request['alamat'];
        $lapangan->harga = $request['harga'];
        $lapangan->handphone = $request['handphone'];
        $lapangan->save();
    }

    /**
     * Display the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function show($id)
    {
        //
    }

    public function coba(Request $request){

      $lapangan = Lapangan::create([
        'nama' => request('nama'),
        'alamat' => request('alamat'),
        'harga' => request('harga'),
        'handphone' => request('handphone')
      ]);

      $params = [
        'grant_type' => 'password',
        'client_id' => $this->client->id,
        'client_secret' => $this->client->secret,
        // 'username' => request('nama'),
        // 'password' => request('harga'),
        // 'nama' => request('nama'),
        // 'alamat' => request('alamat'),
        // 'harga' => request('harga'),
        // 'handphone' => request('handphone'),
        'scope' => '*'
      ];

      $request->request->add($params);

      $proxy = Request::create('oauth/token', 'POST');

      return Route::dispatch($proxy);

       //dd($request->all());
    }

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
        $lapangan = Lapangan::find($id);
        echo json_encode($lapangan);
    }

    /**
     * Update the specified resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function update(Request $request, $id)
    {
        $lapangan = Lapangan::find($id);
        $lapangan->nama = $request['nama'];
        $lapangan->alamat = $request['alamat'];
        $lapangan->harga = $request['harga'];
        $lapangan->handphone = $request['handphone'];
        $lapangan->update();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $lapangan = Lapangan::find($id);
        $lapangan->delete();
    }
}
