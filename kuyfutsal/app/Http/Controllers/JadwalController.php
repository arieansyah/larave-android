<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Jadwal;
use App\Booking;
use App\Status;

class JadwalController extends Controller
{
    /**
     * Display a listing of the resource.
     *
     * @return \Illuminate\Http\Response
     */
    public function index($tahun = null, $bulan = null, $tanggal = null)
    {
      $dt = \Carbon\Carbon::now();
      $tahun = $dt->year;
      $bulan = $dt->month;
      $tanggal = $dt->day;

      $data = array(
          'tahun' => $tahun,
          'bulan' => $bulan,
          'tanggal' => $tanggal,
      );

      return view('jadwal.index', $data);
    }

    public function tes(){
      $dt = \Carbon\Carbon::now();
      $tahun = $dt->year;
      $bulan = $dt->month;
      $tanggal = $dt->day;

      $data = array(
          'tahun' => $tahun,
          'bulan' => $bulan,
          'tanggal' => $tanggal,
      );

      dd($data);
    }

    public function listData($tahun = null, $bulan = null, $tanggal = null)
    {

      $jadwal = Jadwal::leftJoin('bookings', 'bookings.jadwal_id', '=', 'jadwals.id_jadwal')
        ->orderBy('id_jadwal', 'asc')
        ->get();
      $no = 0;
      $data = array();
      foreach($jadwal as $list){
        $no ++;
        $row = array();
        $row[] = $no;
        $row[] = $list->jam;

        if ($list->status == "Booking") {
          $status = '<span class="label label-danger">Book</span>';
        }elseif ($list->status == null) {
          $status = '<span class="label label-primary">Free</span>';
        }else {
          $status = '<span class="label label-warning">Proses...</span>';
        }

        $row[] = $status;
        $status = 1;

        if ($list->status == "Booking" ) {
          $action = '
          <div class="btn-group">
            <a onclick="deleteData('.$list->id_jadwal.')" class="btn btn-default btn-sm"><i class="fa fa-ban"></i> Cancel</a>
          </div>';
        }elseif ($list->status == "Proses...") {
          $action = '
          <div class="btn-group">
            <a onclick="editForm('.$list->id_jadwal.')" id="booking" class="btn btn-danger btn-sm"><i class="fa fa-book"></i> Booking</a>
            <a onclick="deleteData('.$list->id_jadwal.')" class="btn btn-default btn-sm"><i class="fa fa-ban"></i> Cancel</a>
          </div>';
        }else {
          $action = '
          <div class="btn-group">
            <a onclick="booking('.$list->id_jadwal.')" id="booking" class="btn btn-danger btn-sm"><i class="fa fa-book"></i> Booking</a>
            <a onclick="proses('.$list->id_jadwal.')" id="proses" class="btn btn-warning btn-sm"><i class="fa fa-bookmark"></i> Proses...</a>
          </div';
        }

        $row[] = $action;
        $data[] = $row;
      }

      $output = array("data" => $data);
      return response()->json($output);
    }

    public function listData_Mobile(){
        $jadwal = Jadwal::leftJoin('bookings', 'bookings.jadwal_id', '=', 'jadwals.id_jadwal')
          ->orderBy('id_jadwal', 'asc')
          ->get();
        return response()->json($jadwal);
    }

    public function refresh(Request $request)
    {
      $tanggal = $request['tanggal'];
      return view('jadwal.index', compact('tanggal'));
    }

    /**
     * Store a newly created resource in storage.
     *
     * @param  \Illuminate\Http\Request  $request
     * @return \Illuminate\Http\Response
     */
    public function store(Request $req)
    {
      // $booking = new Booking();
      // $booking->jadwal_id = $request->id;
      // $booking->nama = $request->nama;
      // $booking->no_hanphone = $request->no_handphone;
      // $booking->status = $request->status;
      // $booking->username = $request->username;
      // $booking->save();

      Booking::create($req->all());

      //return response()->json('booking'=>$booking);

    }

    public function saveData(){

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

    /**
     * Show the form for editing the specified resource.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function edit($id)
    {
      $jadwal = Jadwal::find($id);
      echo json_encode($jadwal);
    }

    public function editAfter($id){
      $booking = Booking::find($id);
      echo json_encode($booking);
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
      $getStatus = Booking::where('jadwal_id', $id)->where('status', "Proses...")->first();
      $getStatus->status = $request->status;
      $getStatus->update();
    }

    /**
     * Remove the specified resource from storage.
     *
     * @param  int  $id
     * @return \Illuminate\Http\Response
     */
    public function destroy($id)
    {
        $delete = Booking::where('jadwal_id', $id);
        $delete->delete();
    }
}
